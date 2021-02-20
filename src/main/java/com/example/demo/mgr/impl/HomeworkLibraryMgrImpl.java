package com.example.demo.mgr.impl;

import com.example.demo.common.util.DateUtil;
import com.example.demo.dao.HomeworkMapper;
import com.example.demo.dao.po.HomeworkPo;
import com.example.demo.mgr.HomeworkLibraryMgr;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description HomeworkLibraryMgrImpl
 * @date 2021/2/20 17:15
 **/
@Repository
public class HomeworkLibraryMgrImpl implements HomeworkLibraryMgr {

    @Resource
    HomeworkMapper homeworkMapper;
    @Override
    public Long createHomework(String homeworkName, String homeworkNotice,Long creatorId) {
        HomeworkPo homeworkPo=new HomeworkPo();
        homeworkPo.setHomeworkName(homeworkName);
        homeworkPo.setHomeworkNotice(homeworkNotice);
        homeworkPo.setCreatorId(creatorId);
        homeworkPo.setLastModifiedTime(DateUtil.getCurrentTimeStr());
        return homeworkMapper.createHomework(homeworkPo);
    }

    @Override
    public boolean updateProblemIds(Long homeworkId, List<Long> problemIdList) {
        HomeworkPo homeworkPo=new HomeworkPo();
        homeworkPo.setHomeworkId(homeworkId);
        String problemIds = StringUtils.join(problemIdList.toArray(), ",");
        homeworkPo.setProblemIds(problemIds);
        homeworkPo.setLastModifiedTime(DateUtil.getCurrentTimeStr());
        return homeworkMapper.updateProblemIds(homeworkPo);
    }
}
