package com.example.demo.service;

import java.util.List;

/**
 * @description 作业相关Service
 * @date 2021/2/20 16:41
 **/
public interface HomeworkLibraryService {

    /**
     *  创建作业
     */
    Long createHomework(String homeworkName,String homeworkNotice,Long creatorId);

    /**
     *  创建作业
     */
    boolean saveHomework(Long homeworkId, List<Long> problemIdList);
}
