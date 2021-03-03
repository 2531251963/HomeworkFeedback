package com.example.demo.mgr;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.util.DateUtil;
import com.example.demo.common.util.DelayQueueUtil;
import com.example.demo.dao.HomeworkPushJobMapper;
import com.example.demo.dao.po.HomeworkPushJobPo;
import com.example.demo.mgr.bo.DelayDataBo;
import com.example.demo.mgr.bo.EmailContentBo;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 作业通知提醒任务
 * @date 2021/3/3 17:46
 **/
public class HomeworkPushJob  implements CommandLineRunner {

    @Resource
    HomeworkMailSenderMgr homeworkMailSenderMgr;
    @Resource
    HomeworkPushJobMapper homeworkPushJobMapper;

    private void init(){
        List<HomeworkPushJobPo> homeworkPushJobPos = homeworkPushJobMapper.selectUnPushHomeworkJob();
        homeworkPushJobPos.forEach(homeworkPushJobPo -> {
            List<EmailContentBo> emailContentBos = JSON.parseArray(homeworkPushJobPo.getData(), EmailContentBo.class);
            DelayDataBo delayDataBo=new DelayDataBo(DateUtil.toDateLong(homeworkPushJobPo.getDeadlineTime()),emailContentBos);
            DelayQueueUtil.put(delayDataBo);
        });
    }
    @Override
    public void run(String... args) throws Exception {
        init();
        new Thread(() -> {
            while (true){
                DelayDataBo delayDataBo = DelayQueueUtil.take();
             //   homeworkMailSenderMgr.sendMail(delayDataBo.getEmailContentBoList());
            }
        }).start();
    }
}
