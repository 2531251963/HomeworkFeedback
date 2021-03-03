package com.example.demo.common.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.mgr.bo.EmailContentBo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@Slf4j
@Configuration
public class MailUtil {

    /**
     * QQ邮箱服务器
     */
    private final String HOST = "smtp.qq.com";

    /**
     * 自己的邮箱（自定义）
     */
    private final String FROM = "2531251963@qq.com";

    /**
     * 邮箱用户名，即QQ账号（自定义）
     */
    private final String USERNAME = "2531251963@qq.com";

    /**
     * 邮箱授权码（自定义）
     */
    private final String PASSWORD = "ucppdrhpnkewdhif";

    private Session session;

    public String title1 = "作业发布通知";
    public String template1 = "<html> %s同学你好,%s发布了作业:[%s] 请在%s时间前完成并提交作业。<br/>作业详情: <a href='http://www.cnblogs.com/ysocean/p/7666061.html'> </html?";

    public String title2 = "作业未提交通知";
    public String template2 = "%s同学你好,%s发布的作业:[%s] 还未提交,请及时在%s时间前完成并提交作业。<br/>作业详情: <a href='http://www.cnblogs.com/ysocean/p/7666061.html'>";

    @PostConstruct
    void init() {
        String sslFactory = "javax.net.ssl.SSLSocketFactory";
        Properties props = new Properties();
        props.setProperty("mail.smtp.socketFactory.class", sslFactory);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.username", USERNAME);
        props.put("mail.smtp.password", PASSWORD);
        session = Session.getDefaultInstance(props, new Authenticator() {
            //身份认证
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
    }

    public void sendEmail(List<EmailContentBo> emailContentBos) {
        emailContentBos.forEach(emailContentBo-> {
            try {
                InternetAddress[] addresses = {new InternetAddress(emailContentBo.getEmail(),"作业反馈系统","UTF-8")};
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(FROM));
                message.setRecipients(Message.RecipientType.TO, addresses);
                //发送标题（自定义）
                message.setSubject(emailContentBo.getTitle());

                message.setSentDate(new Date());
                //发送内容（自定义）
                message.setContent(emailContentBo.getContent(), "text/html;charset=UTF-8");
                Transport transport = session.getTransport("smtp");
                transport.connect(HOST, USERNAME, PASSWORD);
                Transport.send(message);
                System.out.println("email has been sent "+JSON.toJSONString(emailContentBos));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("邮件发送异常 toEmailList:{}", JSON.toJSONString(emailContentBos), e);
            }
        });
    }

    public void sendEmail2(List<EmailContentBo> emailContentBos) {
        emailContentBos.forEach(emailContentBo-> {
            try {
                MimeMessage message = new MimeMessage(session);
                MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message);
                mimeMessageHelper.setTo(emailContentBo.getEmail());
                mimeMessageHelper.setFrom(FROM);
                mimeMessageHelper.setSubject(emailContentBo.getTitle());
                mimeMessageHelper.setText(emailContentBo.getContent(),true);
                Transport transport = session.getTransport("smtp");
                transport.connect(HOST, USERNAME, PASSWORD);
                Transport.send(message);
                System.out.println("email has been sent "+JSON.toJSONString(emailContentBos));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("邮件发送异常 toEmailList:{}", JSON.toJSONString(emailContentBos), e);
            }
        });
    }
    public static void main(String[] args) {
        MailUtil mailUtil=new MailUtil();
        mailUtil.init();
        List<EmailContentBo> toEmailList = new LinkedList<>();
        EmailContentBo emailContentBo=new EmailContentBo("1004686948@qq.com",mailUtil.title1);
        emailContentBo.createContent(mailUtil.template1,"小明","张老师","数学作业","2021-03-01 15:09:17");
        toEmailList.add(emailContentBo);
     /*   EmailContentBo emailContentBo1=new EmailContentBo("1004686948@qq.com",mailUtil.title2);
        emailContentBo1.createContent(mailUtil.template2,"小张","啊老师","语文作业","2022-03-21 15:09:17");
        toEmailList.add(emailContentBo1);*/
        try {
            mailUtil.sendEmail(toEmailList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}