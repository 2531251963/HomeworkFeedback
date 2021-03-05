package com.example.demo.common.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * @date 2020/11/23 21:37
 **/
@Builder
@Getter
public class DingTalkMarkDownMessage {
    private String title;
    private String text;

    @Override
    public String toString() {
        Map<String, Object> requestMap = Maps.newLinkedHashMap();
        requestMap.put("msgtype", "markdown");

        Map<String, String> markdown = Maps.newLinkedHashMap();
        markdown.put("title", this.title);

        //title不显示，必须用#加在text里面
        String text = "##### " + this.title + "\n" + this.text;
        markdown.put("text", text);
        requestMap.put("markdown", markdown);

        return JSON.toJSONString(requestMap);
    }
}
