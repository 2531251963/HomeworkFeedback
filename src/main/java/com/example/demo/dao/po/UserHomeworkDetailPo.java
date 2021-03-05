package com.example.demo.dao.po;

import com.alibaba.fastjson.JSON;
import com.example.demo.mgr.bo.UserHomeworkAnswerBo;
import com.example.demo.mgr.bo.UserHomeworkDetailBo;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @description UserHomeworkDetailPo
 * @date 2021/3/5 14:39
 **/
@Data
public class UserHomeworkDetailPo {

    private Long detailId;

    private Long publishId;

    private Long homeworkId;

    private Long userId;

    private String status;

    private String answers;

    private Double score;

    private String doneHomeworkJson;


    public UserHomeworkDetailPo convertFromBo(UserHomeworkDetailBo userHomeworkDetailBo){
        this.detailId=userHomeworkDetailBo.getDetailId();
        this.publishId=userHomeworkDetailBo.getPublishId();
        this.homeworkId=userHomeworkDetailBo.getHomeworkId();
        this.userId=userHomeworkDetailBo.getUserId();
        this.status=userHomeworkDetailBo.getStatus();
        if (!CollectionUtils.isEmpty(userHomeworkDetailBo.getAnswers())){
            this.answers= JSON.toJSONString(userHomeworkDetailBo.getAnswers());
        }
        this.doneHomeworkJson=userHomeworkDetailBo.getDoneHomeworkJson();
        return this;
    }
}
