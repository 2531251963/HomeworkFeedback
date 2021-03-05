package com.example.demo.common.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @description 时间工具类
 * @date 2021/1/25 15:45
 **/
public class DateUtil {

    private  static final String FORMAT_STRING="yyyy-MM-dd HH:mm:ss";

    public static String getCurrentTimeStr(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT_STRING);
        return dateTimeFormatter.format(time);
    }


    public static Long toDateLong(String dateTimeStr) {
        SimpleDateFormat sf = new SimpleDateFormat(FORMAT_STRING);
        Long dateTime = null;
        try {
            dateTime = sf.parse(dateTimeStr).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    public static String toDateString(Long dateTime) {
        SimpleDateFormat sf = new SimpleDateFormat(FORMAT_STRING);
        String datesString = null;
        try {
            datesString = sf.format(dateTime);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return datesString;
    }
}
