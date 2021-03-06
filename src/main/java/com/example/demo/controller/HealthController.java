package com.example.demo.controller;

import com.example.demo.common.response.Response;
import com.example.demo.mgr.HomeworkMailSenderMgr;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 健康检查
 **/
@RestController
public class HealthController {

    @RequestMapping("/health")
    public Response<String> health(){
        return Response.ok();
    }

    @Resource
    HomeworkMailSenderMgr homeworkMailSenderMgr;
    @RequestMapping("/sender")
    public void sendMain(){
       // homeworkMailSender.sendMail();
    }
}
