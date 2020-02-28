package com.ihai.accounting.tax.service.impl;

import com.ihai.accounting.tax.common.utils.ExcelUtil;
import com.ihai.accounting.tax.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public InputStream filterSheet(MultipartFile file, Integer filterColNum, String filterContent) throws IOException {
        Workbook workbook = ExcelUtil.getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<Row> retainRows = new ArrayList<Row>();
        Sheet retianSheet = null;
        if(workbook != null){
            for(int sheetNum = 0,sheetLen=workbook.getNumberOfSheets();sheetNum < sheetLen;sheetNum++) {
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                String sheetName= sheet.getSheetName();
                if (sheet == null || sheetName.indexOf("公司") == -1) {
                    continue;
                }
                if(retianSheet == null){
                    retianSheet = sheet;
                }
                //获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum;rowNum <= lastRowNum;rowNum++){
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    //标题
                    if(retainRows.size() < 5 && rowNum < 5){
                        retainRows.add(row);
                    }
                    if(row != null && row.getCell(filterColNum) != null && ExcelUtil.getCellValue(row.getCell(filterColNum)).equals(filterContent)){
                        Cell lastCell = row.createCell(row.getLastCellNum());
                        lastCell.setCellValue(sheetName);
                        lastCell.setCellStyle(row.getCell(0).getCellStyle());
                        lastCell.getCellStyle().setAlignment(CellStyle.ALIGN_RIGHT);
                        retainRows.add(row);
                    }
                }
            }
            workbook.close();
        }
        Workbook workbook1 = ExcelUtil.create(file.getOriginalFilename());
        Sheet sheet = workbook1.createSheet("66020106");
        sheet.setColumnWidth(8, 256 * 15);
        ExcelUtil.mergeSheetAllRegion(retianSheet, sheet);
        for(int i=0,len=retainRows.size(); i<len; i++){
            Row retainRow = retainRows.get(i);
            Row row = sheet.createRow(i);
            ExcelUtil.copyRow(workbook1, retainRow, row, true);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook1.write(bos);
        byte[] barray = bos.toByteArray();
        InputStream is = new ByteArrayInputStream(barray);
        workbook1.close();
        bos.close();
        is.close();
        return is;
    }


}
