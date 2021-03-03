package com.example.demo.dao.po;

import com.example.demo.common.util.DateUtil;
import com.example.demo.common.util.StringCaseUtil;
import com.example.demo.mgr.bo.HomeworkPublishBo;
import lombok.Data;

/**
 * @description PublishHomeworkRecordPo
 * @date 2021/3/2 18:46
 **/
@Data
public class PublishHomeworkRecordPo {

    private Long publishId;

    private Long homeworkId;

    private String classIds;

    private Long publisherId;

    private String deadlineTime;

    private String publishTime;

    public void convertFromBo(HomeworkPublishBo homeworkPublishBo){
        this.homeworkId=homeworkPublishBo.getHomeworkId();
        this.classIds= StringCaseUtil.listSplitString(homeworkPublishBo.getClassIds());
        this.publisherId=homeworkPublishBo.getPublisherId();
        this.deadlineTime= DateUtil.toDateString(homeworkPublishBo.getDeadlineTime());
        this.publishTime= DateUtil.toDateString(homeworkPublishBo.getPublishTime());
    }
}
