package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.bean.PageParam;
import com.example.demo.common.bean.PageResultInfo;
import com.example.demo.common.response.ResponseCode;
import com.example.demo.mgr.bo.HomeworkBo;
import com.example.demo.mgr.bo.LibraryHomeworkDetailBo;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * @description 作业相关Service
 * @date 2021/2/20 16:41
 **/
public interface HomeworkLibraryService {

    /**
     * 创建作业
     */
    Long createHomework(String homeworkName, String homeworkNotice, Long creatorId);

    /**
     * 创建作业
     */
    boolean saveHomework(Long homeworkId, List<Long> problemIdList);

    /**
     * 作业库列表
     */
    PageResultInfo<HomeworkBo> homeworkListPage(Long userId, PageParam pageParam);

    /**
     * 创建题目
     */
    Pair<ResponseCode, Long> createProblem(Integer problemType, JSONObject content, Long userId);

    /**
     * 编辑题目
     */
    Pair<ResponseCode, Boolean> editorProblem(Long problemId,Integer problemType, JSONObject content);

    /**
     * 作业库中的作业详情
     */
    LibraryHomeworkDetailBo homeworkDetail(Long homeworkId);

    /**
     * 作业库中的作业详情
     */
    boolean publishHomework(Long homeworkId,List<Integer> classIds,Long deadlineTime,Long userId);


    /**
     * 作业发布记录
     */
    boolean homeworkPublishRecordList(Long userId, PageParam pageParam);
}
