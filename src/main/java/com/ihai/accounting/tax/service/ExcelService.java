package com.ihai.accounting.tax.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface ExcelService {

    InputStream filterSheet(MultipartFile file, Integer filterColNum, String filterContent) throws IOException;
}
