package com.ks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final String YYYY_MM_DD_HH_DD_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static Date parse(String dateStr) throws ParseException {
        return parse(dateStr, YYYY_MM_DD_HH_DD_SS);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(dateStr);
    }

    public static String format(Date date) {
        return format(date, YYYY_MM_DD_HH_DD_SS);
    }

    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }
}
