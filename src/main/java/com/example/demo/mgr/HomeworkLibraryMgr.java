package com.example.demo.mgr;

import java.util.List;

/**
 * @description HomeworkLibraryMgr
 * @date 2021/2/20 17:12
 **/

public interface HomeworkLibraryMgr {

    /**
     * 创建作业
    */
    Long createHomework(String homeworkName, String homeworkNotice,Long creatorId);


    /**
     * 作业更改题目
     */
    boolean updateProblemIds(Long homeworkId, List<Long> problemIdList);
}
