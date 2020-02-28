package com.ihai.accounting.tax.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static Log logger = LogFactory.getLog(DateUtil.class);

    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(Date date, String formatstr) {

        SimpleDateFormat df = new SimpleDateFormat(formatstr);
        String result = "";
        if (date == null) {
            return result;
        }
        try {
            result = df.format(date);
        } catch (Exception e) {
            logger.error(e);
        }

        return result;
    }
}
