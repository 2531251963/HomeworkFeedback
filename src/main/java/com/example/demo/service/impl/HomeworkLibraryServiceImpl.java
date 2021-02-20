package com.example.demo.service.impl;

import com.example.demo.mgr.HomeworkLibraryMgr;
import com.example.demo.service.HomeworkLibraryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description HomeworkLibraryServiceImpl
 * @date 2021/2/20 16:42
 **/
@Service("homeworkLibraryServiceImpl")
public class HomeworkLibraryServiceImpl implements HomeworkLibraryService {

    @Resource
    HomeworkLibraryMgr homeworkLibraryMgr;
    @Override
    public Long createHomework(String homeworkName, String homeworkNotice,Long creatorId) {
        return homeworkLibraryMgr.createHomework(homeworkName,homeworkNotice,creatorId);
    }

    @Override
    public boolean saveHomework(Long homeworkId, List<Long> problemIdList) {
        return homeworkLibraryMgr.updateProblemIds(homeworkId,problemIdList);
    }
}
