package com.example.demo.mgr.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.bean.PageParam;
import com.example.demo.common.bean.PageResultInfo;
import com.example.demo.common.util.DateUtil;
import com.example.demo.common.util.StringCaseUtil;
import com.example.demo.dao.*;
import com.example.demo.dao.po.HomeworkPo;
import com.example.demo.dao.po.ProblemPo;
import com.example.demo.dao.po.PublishClassRecordPo;
import com.example.demo.dao.po.PublishHomeworkRecordPo;
import com.example.demo.mgr.HomeworkLibraryMgr;
import com.example.demo.mgr.bo.HomeworkBo;
import com.example.demo.mgr.bo.HomeworkPublishBo;
import com.example.demo.mgr.bo.problem.AbstractProblemBo;
import com.example.demo.mgr.bo.problem.ProblemFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description HomeworkLibraryMgrImpl
 * @date 2021/2/20 17:15
 **/
@Repository
public class HomeworkLibraryMgrImpl implements HomeworkLibraryMgr {

    @Resource
    HomeworkMapper homeworkMapper;
    @Resource
    ProblemMapper problemMapper;
    @Resource
    PublishHomeworkRecordMapper publishHomeworkRecordMapper;
    @Resource
    PublishClassRecordMapper publishClassRecordMapper;
    @Override
    public Long createHomework(String homeworkName, String homeworkNotice, Long creatorId) {
        HomeworkPo homeworkPo = new HomeworkPo();
        homeworkPo.setHomeworkName(homeworkName);
        homeworkPo.setHomeworkNotice(homeworkNotice);
        homeworkPo.setCreatorId(creatorId);
        homeworkPo.setLastModifiedTime(DateUtil.getCurrentTimeStr());
        homeworkMapper.createHomework(homeworkPo);
        return homeworkPo.getHomeworkId();
    }

    @Override
    public boolean updateProblemIds(Long homeworkId, List<Long> problemIdList) {
        HomeworkPo homeworkPo = new HomeworkPo();
        homeworkPo.setHomeworkId(homeworkId);
        String problemIds = StringCaseUtil.listSplitString(problemIdList);
        homeworkPo.setProblemIds(problemIds);
        homeworkPo.setLastModifiedTime(DateUtil.getCurrentTimeStr());
        return homeworkMapper.updateProblemIds(homeworkPo);
    }

    @Override
    public PageResultInfo<HomeworkBo> getHomeworkListPage(Long userId, PageParam pageParam) {
        PageHelper.startPage(pageParam.getCurrentPage(), pageParam.getPageSize());
        List<HomeworkPo> homeworkPos = homeworkMapper.selectHomeworkListByCreatorId(userId);
        PageInfo<HomeworkPo> pageInfo = new PageInfo<>(homeworkPos);
        HomeworkBo homeworkBo = new HomeworkBo();
        List<HomeworkBo> homeworkBos = pageInfo.getList().stream().map(homeworkBo::convertFromHomeworkPo).collect(Collectors.toList());
        return new PageResultInfo<>(pageInfo,homeworkBos);
    }

    @Override
    public HomeworkBo getHomeworkBo(Long homeworkId) {
        HomeworkPo homeworkPo = homeworkMapper.selectHomeworkListByhomeworkId(homeworkId);
        HomeworkBo homeworkBo = new HomeworkBo().convertFromHomeworkPo(homeworkPo);
        return homeworkBo;
    }

    @Override
    public Long createProblem(AbstractProblemBo abstractProblemBo, Long userId) {
        ProblemPo problemPo = new ProblemPo();
        problemPo.setProblemType(abstractProblemBo.getProblemType());
        problemPo.setContent(abstractProblemBo.getContent().toJSONString());
        problemPo.setCreatorId(userId);
        problemMapper.createProblem(problemPo);
        return problemPo.getProblemId();
    }

    @Override
    public boolean editorProblem(AbstractProblemBo abstractProblemBo) {
        ProblemPo problemPo = new ProblemPo();
        problemPo.setProblemId(abstractProblemBo.getProblemId());
        problemPo.setContent(abstractProblemBo.getContent().toJSONString());
        return problemMapper.updateProblemContent(problemPo);
    }

    @Override
    public List<AbstractProblemBo> getAbstractProblemBoList(List<Long> problemIds) {
        List<ProblemPo> problemPos = problemMapper.selectProblemList(problemIds);
        List<AbstractProblemBo> abstractProblemBos = new LinkedList<>();
        problemPos.forEach(x -> {
            AbstractProblemBo abstractProblemBo = ProblemFactory.getProblemBo(x.getProblemType());
            abstractProblemBo.setData(x.getProblemId(), x.getProblemType(), JSON.parseObject(x.getContent()));
            abstractProblemBo.assembleData();
            abstractProblemBos.add(abstractProblemBo);
        });
        return abstractProblemBos;
    }

    @Override
    public Long homeworkPublish(HomeworkPublishBo homeworkPublishBo) {
        PublishHomeworkRecordPo homeworkRecordPo = new PublishHomeworkRecordPo();
        homeworkRecordPo.convertFromBo(homeworkPublishBo);
        publishHomeworkRecordMapper.insertPublishHomeworkRecord(homeworkRecordPo);
        Long publishId = homeworkRecordPo.getPublishId();
        List<PublishClassRecordPo> publishClassRecordPoList = homeworkPublishBo.getClassIds().stream().map(x -> new PublishClassRecordPo(publishId, x)).collect(Collectors.toList());
        publishClassRecordMapper.batchInsertPublishClassRecord(publishClassRecordPoList);
        return publishId;
    }
}
