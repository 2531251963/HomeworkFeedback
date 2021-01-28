package com.example.demo.common.util;

import com.example.demo.common.response.Response;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.StringEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liyihe
 * @description 钉钉播报
 * @date 2020/11/23 21:38
 **/
@Component
@Slf4j
public class ErrorBroadCastUtils {
    @Value("${dingding.webhookUrl}")
    private String webhookUrl;
    @Value("${spring.profiles.active}")
    private String active;
    private final ExecutorService dingdingAlarmExecuter = Executors.newFixedThreadPool(1, new ThreadFactoryBuilder().setNameFormat("Broadcast-Pool").build());

    /**
     * 钉钉机器人目前必须选择安全设置:
     * （三种1:自定义关键词 2:加签 3:IP地址）
     * 项目中选用的第一种，配置关键词为"帮买中心"
     * 最多可以设置10个关键词，消息中至少包含其中1个关键词才可以发送成功。
     */
    public void broadcast(ProceedingJoinPoint pjp, Response resp) {
        final String title = String.format("%s %s环境 接口调用失败：", DateUtil.getCurrentTimeStr(), active)
                + "日志id:" + MDC.get("request_id");

        final StringBuilder text = new StringBuilder();
        text.append("1. 调用接口：").append(getCaller(pjp)).append("\n");
        text.append("3. 失败原因：").append(resp.toString()).append("\n");
        broadMarkdownMessage(title, text);
    }

    /**
     * 获取接口调用者
     *
     * @param pjp
     * @return
     */
    public static String getCaller(ProceedingJoinPoint pjp) {
        // 获取简单类名
        String className = pjp.getSignature().getDeclaringTypeName();
        String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
        // 获取方法名
        String methodName = pjp.getSignature().getName();
        return simpleClassName + "." + methodName;
    }

    private void broadMarkdownMessage(final String title, final StringBuilder text) {
        dingdingAlarmExecuter.submit(() -> {
            DingTalkMarkDownMessage msg = DingTalkMarkDownMessage.builder()
                    .title(title)
                    .text(text.toString())
                    .build();
            try {
                String json = HttpUtils.httpPost(webhookUrl, new StringEntity(msg.toString(), StandardCharsets.UTF_8));
                log.info(json);
            } catch (Exception e) {
                log.error("broadMarkdownMessage error", e);
            }
        });
    }

    /**
     * 异常播报
     *
     * @param errName
     * @param errMsg
     */
    public void broadException(String errName, String errMsg) {
        final String title = String.format("%s 异常播报：", DateUtil.getCurrentTimeStr());
        final StringBuilder text = new StringBuilder();
        text.append(errName).append("|").append(errMsg).append("\n");
        broadMarkdownMessage(title, text);
    }
}
