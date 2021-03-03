package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.response.ResponseCode;
import com.example.demo.common.util.DateUtil;
import com.example.demo.common.util.DelayQueueUtil;
import com.example.demo.mgr.ClassUserMgr;
import com.example.demo.mgr.HomeworkLibraryMgr;
import com.example.demo.mgr.UserMgr;
import com.example.demo.mgr.bo.*;
import com.example.demo.mgr.bo.problem.AbstractProblemBo;
import com.example.demo.mgr.bo.problem.ProblemFactory;
import com.example.demo.mgr.impl.HomeworkMailSenderMgrImpl;
import com.example.demo.service.HomeworkLibraryService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @description HomeworkLibraryServiceImpl
 * @date 2021/2/20 16:42
 **/
@Service("homeworkLibraryServiceImpl")
public class HomeworkLibraryServiceImpl implements HomeworkLibraryService {

    @Resource
    HomeworkLibraryMgr homeworkLibraryMgr;
    @Resource
    ClassUserMgr classUserMgr;
    @Resource
    UserMgr userMgr;
    @Resource
    HomeworkMailSenderMgrImpl homeworkMailSenderMgr;

    @Override
    public Long createHomework(String homeworkName, String homeworkNotice, Long creatorId) {
        return homeworkLibraryMgr.createHomework(homeworkName, homeworkNotice, creatorId);
    }

    @Override
    public boolean saveHomework(Long homeworkId, List<Long> problemIdList) {
        return homeworkLibraryMgr.updateProblemIds(homeworkId, problemIdList);
    }

    @Override
    public List<HomeworkBo> homeworkList(Long userId) {
        return homeworkLibraryMgr.getHomeworkList(userId);
    }

    @Override
    public Pair<ResponseCode, Long> createProblem(Integer problemType, JSONObject content, Long userId) {
        AbstractProblemBo problemBo = ProblemFactory.getProblemBo(problemType);
        problemBo.setData(null, problemType, content);
        problemBo.assembleData();
        ResponseCode validResult = problemBo.validData();
        if (!validResult.equals(ResponseCode.OK)) {
            return Pair.of(validResult, null);
        }
        Long problemId = homeworkLibraryMgr.createProblem(problemBo, userId);
        return Pair.of(ResponseCode.OK, problemId);
    }

    @Override
    public Pair<ResponseCode, Boolean> editorProblem(Long problemId, Integer problemType, JSONObject content) {
        AbstractProblemBo problemBo = ProblemFactory.getProblemBo(problemType);
        problemBo.setData(problemId, problemType, content);
        problemBo.assembleData();
        ResponseCode validResult = problemBo.validData();
        if (!validResult.equals(ResponseCode.OK)) {
            return Pair.of(validResult, null);
        }
        boolean result = homeworkLibraryMgr.editorProblem(problemBo);
        return Pair.of(ResponseCode.OK, result);
    }

    @Override
    public LibraryHomeworkDetailBo homeworkDetail(Long homeworkId) {
        LibraryHomeworkDetailBo libraryHomeworkDetailBo = new LibraryHomeworkDetailBo();
        HomeworkBo homeworkBo = homeworkLibraryMgr.getHomeworkBo(homeworkId);
        List<AbstractProblemBo> abstractProblemBos = homeworkLibraryMgr.getAbstractProblemBoList(homeworkBo.getProblemIds());
        libraryHomeworkDetailBo.assembleData(homeworkBo, abstractProblemBos);
        return libraryHomeworkDetailBo;
    }

    @Override
    public boolean publishHomework(Long homeworkId, List<Integer> classIds, Long deadlineTime, Long userId) {
        HomeworkPublishBo homeworkPublishBo = new HomeworkPublishBo(homeworkId, classIds, userId, deadlineTime);
        //发布作业
        homeworkLibraryMgr.homeworkPublish(homeworkPublishBo);
        //获取作业信息
        HomeworkBo homeworkBo = homeworkLibraryMgr.getHomeworkBo(homeworkId);
        //获取班级信息
        List<ClassBo> classBos = classUserMgr.getClassBoListByClassIds(classIds);
        HomeworkMailSenderMgrImpl.MailMessage message1 = homeworkMailSenderMgr.getMailMessage1();
        HomeworkMailSenderMgrImpl.MailMessage message2 = homeworkMailSenderMgr.getMailMessage2();
        //获取发布人(教师)信息
        UserBo teacherBo = userMgr.getUserInfoByUserId(userId);
        // emailContentBoList1:立即邮件推送内容  emailContentBoList2:定时任务邮件推送内容
        List<EmailContentBo> emailContentBoList1 = new LinkedList<>();
        List<EmailContentBo> emailContentBoList2 = new LinkedList<>();
        classBos.forEach(classBo -> {
            List<Long> studentUserIds = classBo.getStudentUserIds();
            List<EmailContentBo> emailContentBos1 = new LinkedList<>();
            List<EmailContentBo> emailContentBos2 = new LinkedList<>();
            studentUserIds.forEach(studentUserId -> {
                //获取班级内的学生信息
                UserBo userBo = userMgr.getUserInfoByUserId(studentUserId);
                EmailContentBo emailContentBo1 = new EmailContentBo(userBo.getEmail(), message1.getTitle());
                emailContentBo1.createContent(message1.getContent(), userBo.getUserName(), teacherBo.getUserName(), homeworkBo.getHomeworkName(), DateUtil.toDateString(deadlineTime));
                EmailContentBo emailContentBo2 = new EmailContentBo(userBo.getEmail(), message2.getTitle());
                emailContentBo2.createContent(message2.getContent(), userBo.getUserName(), teacherBo.getUserName(), homeworkBo.getHomeworkName(), DateUtil.toDateString(deadlineTime));
                emailContentBos1.add(emailContentBo1);
                emailContentBos2.add(emailContentBo2);
            });
            //合并
            if (!CollectionUtils.isEmpty(studentUserIds)){
                emailContentBoList1.addAll(emailContentBos1);
                emailContentBoList2.addAll(emailContentBos2);
            }
        });
        homeworkMailSenderMgr.sendMail(emailContentBoList1);
        if (!CollectionUtils.isEmpty(emailContentBoList2)){
            //作业截止通知添加到延时队列
            DelayDataBo delayDataBo=new DelayDataBo(deadlineTime,emailContentBoList2);
            DelayQueueUtil.put(delayDataBo);
        }
        return true;
    }
}
