package com.example.demo.mgr;

import com.example.demo.common.bean.PageParam;
import com.example.demo.common.bean.PageResultInfo;
import com.example.demo.mgr.bo.HomeworkBo;
import com.example.demo.mgr.bo.HomeworkPublishBo;
import com.example.demo.mgr.bo.problem.AbstractProblemBo;

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

    /**
     * 获取某个教师的作业库的作业列表
     */
    PageResultInfo<HomeworkBo> getHomeworkListPage(Long userId, PageParam pageParam);


    /**
     * 获取某个教师的作业库的作业信息
     */
    HomeworkBo getHomeworkBo(Long homeworkId);

    /**
     * 创建题目
     */
    Long createProblem(AbstractProblemBo abstractProblemBo,Long userId);


    /**
     * 编辑题目
     */
    boolean editorProblem(AbstractProblemBo abstractProblemBo);

    /**
     * 批量获取题目 List<AbstractProblemBo>
     */
    List<AbstractProblemBo> getAbstractProblemBoList(List<Long> problemIds);

    /**
     * 作业发布
     */
    Long homeworkPublish(HomeworkPublishBo homeworkPublishBo);
}
