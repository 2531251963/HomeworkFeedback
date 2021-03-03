package com.example.demo.common.constant;

/**
 **/
public class Constant {

    public static final int ONE_DAY=1;


    public static final String title1 = "作业发布通知";
    public static final String template1 = "<html><head></head> <body>" +
            "  <h2>你好: %s同学</h2><br/>  %s发布了作业:[%s] 请在%s时间前完成并提交作业。<br/> 详情:<a href='http://www.cnblogs.com/ysocean/p/7666061.html'>作业详情</a>" +
            "</body></html>";

    public static final String title2 = "作业未提交通知";
    public static final String template2 = "<html><head></head> <body>" +
            "  <h2>你好: %s同学</h2><br/>  %s发布的作业:[%s] 还未提交,请及时在%s时间前完成并提交作业。<br/>详情: <a href='http://www.cnblogs.com/ysocean/p/7666061.html'>作业详情</a>" +
            "</body></html>";
}
