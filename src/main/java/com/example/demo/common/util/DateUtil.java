package com.example.demo.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author liyihe
 * @version 1.0
 * @className DateUtil
 * @description TODO
 * @date 2021/1/25 15:45
 **/
public class DateUtil {

    public static String getCurrentTimeStr(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(time);
    }
}
