package com.ylkj.xxb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 精确到秒
     *
     * @param dateTime yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parseDateTime(String dateTime) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @param date 自定义时间
     * @return 返回格式化的日期串 yyyy-MM-dd
     */
    public static String getDate(Date date) {
        return getDate(date, "yyyy-MM-dd");
    }

    /**
     * @param date   自定义时间
     * @param format 自定义格式
     * @return 返回格式化的日期串
     */
    public static String getDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

}
