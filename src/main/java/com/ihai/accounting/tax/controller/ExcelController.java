package com.ihai.accounting.tax.controller;

import com.ihai.accounting.tax.common.utils.OssUtil;
import com.ihai.accounting.tax.service.ExcelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/api/v1/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @RequestMapping(value = {"/sheet-filter/upload"}, method = RequestMethod.POST)
    public ResponseEntity sheetFilterUpload(MultipartFile file, Integer filterColNum, String filterContent) throws IOException {
        if (file == null || StringUtils.isEmpty(file.getOriginalFilename()) || filterColNum == null || StringUtils.isEmpty(filterContent)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        InputStream is = excelService.filterSheet(file, filterColNum-1, filterContent);
        OssUtil.upload(file.getOriginalFilename(), is);
        return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.OK);
    }
}
