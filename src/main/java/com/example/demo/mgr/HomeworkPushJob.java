package com.example.demo.mgr;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.constant.HomeworkStatusConstant;
import com.example.demo.common.util.DateUtil;
import com.example.demo.common.util.DelayQueueUtil;
import com.example.demo.dao.HomeworkPushJobMapper;
import com.example.demo.dao.UserHomeworkDetailMapper;
import com.example.demo.dao.po.HomeworkPushJobPo;
import com.example.demo.dao.po.UserHomeworkDetailPo;
import com.example.demo.mgr.bo.DelayDataBo;
import com.example.demo.mgr.bo.EmailContentBo;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description 作业通知提醒任务
 * @date 2021/3/3 17:46
 **/
@Slf4j
@Component
public class HomeworkPushJob  implements CommandLineRunner {

    @Resource
    HomeworkMailSenderMgr homeworkMailSenderMgr;
    @Resource
    HomeworkPushJobMapper homeworkPushJobMapper;
    @Resource
    UserHomeworkDetailMapper userHomeworkDetailMapper;


    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("homeworkPushJob-pool-%d").build();
    ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    private void init(){
        List<HomeworkPushJobPo> homeworkPushJobPos = homeworkPushJobMapper.selectUnPushHomeworkJob();
        homeworkPushJobPos.forEach(homeworkPushJobPo -> {
            List<EmailContentBo> emailContentBos = JSON.parseArray(homeworkPushJobPo.getData(), EmailContentBo.class);
            DelayDataBo delayDataBo=new DelayDataBo(DateUtil.toDateLong(homeworkPushJobPo.getDeadlineTime()),homeworkPushJobPo.getPublishId(),emailContentBos);
            DelayQueueUtil.put(delayDataBo);
        });
    }
    @Override
    public void run(String... args) {
        init();
        log.info("HomeworkPushJob 初始化完成");
        singleThreadPool.submit(new HomeworkPushThread());
        log.info("HomeworkPushThread 启动完成");
    }
    class HomeworkPushThread implements  Runnable{
        @Override
        public void run() {
            while (true){
                DelayDataBo delayDataBo = DelayQueueUtil.take();
                log.info("HomeworkPushThread 到时 delayDataBo:{}",JSON.toJSONString(delayDataBo));
                List<EmailContentBo> emailContentBoList = delayDataBo.getEmailContentBoList();
                Iterator<EmailContentBo> iterator = emailContentBoList.iterator();
                while (iterator.hasNext()) {
                    EmailContentBo emailContentBo = iterator.next();
                    String status = null;
                    UserHomeworkDetailPo userHomeworkStatus = userHomeworkDetailMapper.getUserHomeworkStatus(delayDataBo.getPublishId(), emailContentBo.getUserId());
                    if (userHomeworkStatus!=null){
                        status=userHomeworkStatus.getStatus();
                    }
                    if (!HomeworkStatusConstant.UN_START.equals(status) && HomeworkStatusConstant.DOING.equals(status)) {
                        iterator.remove();
                    }
                }
                log.info("HomeworkPushThread 通知 emailContentBoList:{}",JSON.toJSONString(emailContentBoList));
                homeworkMailSenderMgr.sendMail(emailContentBoList);
                homeworkPushJobMapper.updateHomeworkPushJobPoStatus(new HomeworkPushJobPo().setPublishId(delayDataBo.getPublishId()).setStatus(1));
            }
        }
    }
}
