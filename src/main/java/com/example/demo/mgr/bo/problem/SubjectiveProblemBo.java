package com.example.demo.mgr.bo.problem;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.response.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @description 主观题bo
 * @date 2021/2/25 15:37
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SubjectiveProblemBo extends AbstractProblemBo {

    private Long problemId;

    private Integer problemType;

    private Double score;

    private String body;

    private Boolean hasRemark;

    private String remark;

    @Override
    public void assembleData() {
        this.problemId = super.getProblemId();
        this.problemType = super.getProblemType();
        JSONObject content = super.getContent();
        this.score = content.getDouble("score");
        this.body = content.getString("body");
        this.hasRemark = content.getBoolean("has_remark");
        this.remark = content.getString("remark");
    }

    @Override
    public ResponseCode validData() {
        if (score == null || score == 0) {
            return ResponseCode.PROBLEM_SCORE_INCORRECT;
        }
        if (StringUtils.isBlank(body)) {
            return ResponseCode.PROBLEM_BODY_INCORRECT;
        }

        if (hasRemark == null) {
            return ResponseCode.PROBLEM_HAS_REMARK_INCORRECT;
        }

        if (hasRemark) {
            if (StringUtils.isEmpty(remark)) {
                return ResponseCode.PROBLEM_REMARK_INCORRECT;
            }
        }
        return ResponseCode.OK;
    }
}
