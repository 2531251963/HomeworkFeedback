package com.example.demo.mgr.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description 作业发布bo
 * @date 2021/3/2 18:07
 **/
@Data
@NoArgsConstructor
public class HomeworkPublishBo {

    private Long publishId;

    private Long homeworkId;

    private List<Integer> classIds;

    private Long publisherId;

    private Long deadlineTime;

    private Long publishTime;

    public HomeworkPublishBo(Long homeworkId,List<Integer> classIds,Long publisherId,Long deadlineTime){
        this.homeworkId=homeworkId;
        this.classIds=classIds;
        this.publisherId=publisherId;
        this.deadlineTime=deadlineTime;
        this.publishTime=System.currentTimeMillis();
    }
}
