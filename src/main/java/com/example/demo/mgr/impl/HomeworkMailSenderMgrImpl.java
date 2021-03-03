package com.example.demo.mgr.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.constant.Constant;
import com.example.demo.mgr.HomeworkMailSenderMgr;
import com.example.demo.mgr.bo.EmailContentBo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

/**
 * @description HomeworkMailSender
 * @date 2021/3/3 14:59
 **/
@Slf4j
@Component
@Data
public class HomeworkMailSenderMgrImpl implements HomeworkMailSenderMgr {


    @Autowired
    JavaMailSender mailSender;

    private   MailMessage mailMessage1=new MailMessage(Constant.title1,Constant.template1);
    private  MailMessage mailMessage2=new MailMessage(Constant.title2,Constant.template2);

    @Override
    public void sendMail(List<EmailContentBo> emailContentBoList) {
        emailContentBoList.forEach(emailContentBo -> {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
                mimeMessageHelper.setTo(emailContentBo.getEmail());
                mimeMessageHelper.setFrom("2531251963@qq.com");
                mimeMessageHelper.setSubject(emailContentBo.getTitle());
                mimeMessageHelper.setText(emailContentBo.getContent(), true);
                mimeMessageHelper.setSentDate(new Date());
                mailSender.send(message);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("邮件发送异常 emailContentBo:{}", JSON.toJSONString(emailContentBo), e);
            }
        });
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MailMessage{
        private String title;
        private String content;
    }
}
