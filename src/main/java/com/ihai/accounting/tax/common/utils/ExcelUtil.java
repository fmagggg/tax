package com.ihai.accounting.tax.common.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ExcelUtil {

    public static Workbook create(String fileName){
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        if(fileName == null || fileName.endsWith("xls")){
            //2003
            workbook = new HSSFWorkbook();
        }else{
            //2007
            workbook = new XSSFWorkbook();
        }
        return workbook;
    }

    public static Workbook getWorkBook(MultipartFile file) throws IOException {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        //获取excel文件的io流
        InputStream is = file.getInputStream();
        //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
        if(fileName.endsWith("xls")){
            //2003
            workbook = new HSSFWorkbook(is);
        }else if(fileName.endsWith("xlsx")){
            //2007
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    public static void copyCellStyle(CellStyle fromStyle, CellStyle toStyle) {
        toStyle.cloneStyleFrom(fromStyle);
    }

    public static void mergeSheetAllRegion(Sheet fromSheet, Sheet toSheet)  {//合并单元格
        int num = fromSheet.getNumMergedRegions();
        CellRangeAddress cellR = null;
        for (int i = 0; i < num; i++) {
            cellR = fromSheet.getMergedRegion(i);
            toSheet.addMergedRegion(cellR);
        }
    }

    public static void copyCell(Workbook wb, Cell fromCell, Cell toCell, boolean hasValue) {
        //样式
        if(fromCell.getCellStyle() != null){
            CellStyle newstyle=wb.createCellStyle();
            copyCellStyle(fromCell.getCellStyle(), newstyle);
            toCell.setCellStyle(newstyle);
        }
        //评论
        if (fromCell.getCellComment() != null) {
            toCell.setCellComment(fromCell.getCellComment());
        }
        // 不同数据类型处理
        int srcCellType = fromCell.getCellType();
        toCell.setCellType(srcCellType);
        if(hasValue){
            if (srcCellType == HSSFCell.CELL_TYPE_NUMERIC) {
                if (HSSFDateUtil.isCellDateFormatted(fromCell)) {
                    toCell.setCellValue(fromCell.getDateCellValue());
                } else {
                    toCell.setCellValue(fromCell.getNumericCellValue());
                }
            } else if (srcCellType == HSSFCell.CELL_TYPE_STRING) {
                toCell.setCellValue(fromCell.getRichStringCellValue());
            } else if (srcCellType == HSSFCell.CELL_TYPE_BLANK) {
                // nothing21
            } else if (srcCellType == HSSFCell.CELL_TYPE_BOOLEAN) {
                toCell.setCellValue(fromCell.getBooleanCellValue());
            } else if (srcCellType == HSSFCell.CELL_TYPE_ERROR) {
                toCell.setCellErrorValue(fromCell.getErrorCellValue());
            } else if (srcCellType == HSSFCell.CELL_TYPE_FORMULA) {
                toCell.setCellFormula(fromCell.getCellFormula());
            } else { // nothing29
            }
        }
    }

    public static void copyRow(Workbook wb, Row fromRow, Row toRow, boolean hasValue){
        toRow.setHeight(fromRow.getHeight());
        for(int j=fromRow.getFirstCellNum(),len1=fromRow.getLastCellNum(); j<len1; j ++){
            Cell tmpCell = fromRow.getCell(j);
            Cell newCell = toRow.createCell(j);
            copyCell(wb, tmpCell, newCell, hasValue);
        }
    }

    public static void copySheet(Workbook wb, Sheet fromSheet, Sheet toSheet, boolean hasValue) {
        mergeSheetAllRegion(fromSheet, toSheet);
        //设置列宽
        for(int i=0;i<=fromSheet.getRow(fromSheet.getFirstRowNum()).getLastCellNum();i++){
            toSheet.setColumnWidth(i,fromSheet.getColumnWidth(i));
        }
        for (Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext();) {
            Row oldRow = (Row) rowIt.next();
            Row newRow = toSheet.createRow(oldRow.getRowNum());
            copyRow(wb,oldRow,newRow, hasValue);
        }
    }
}
