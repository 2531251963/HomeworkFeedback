package com.example.demo.mgr.bo.problem;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.response.ResponseCode;
import lombok.Data;

/**
 * @description AbstractProblemBo
 * @date 2021/2/24 15:09
 **/
@Data
public abstract class AbstractProblemBo {

    private Long problemId;

    private Integer problemType;

    private JSONObject content;

    public void setData(Long problemId,Integer problemType,JSONObject content){
        this.problemId=problemId;
        this.problemType=problemType;
        this.content=content;
    }
    /**
     * 组装数据
    */
    public abstract void assembleData();

    /**
     * 校验数据正确性
     */
    public abstract ResponseCode validData();


    public abstract Double getScore();
}
