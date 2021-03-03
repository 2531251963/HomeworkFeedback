package com.example.demo.mgr;

import com.example.demo.mgr.bo.EmailContentBo;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @description 作业邮件发送mgr
 * @date 2021/3/3 16:20
 **/
public interface HomeworkMailSenderMgr {

    @Async
    void sendMail(List<EmailContentBo> emailContentBoList);
}
