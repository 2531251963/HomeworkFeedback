package com.example.demo.common.util;

import org.apache.commons.lang3.StringUtils;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description 字符串工具类
 * @date 2021/2/23 18:36
 **/
public class StringCaseUtil {

    public static String listSplitString(List list) {
        return StringUtils.join(list.toArray(), ",");
    }
    public static List<Long> stringSplitList(String str) {
        if (StringUtils.isBlank(str)){
            return Collections.emptyList();
        }
        return Arrays.stream(str.split(",")).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());

    }

}
