package com.example.demo.mgr.bo.problem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.common.response.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description 填空题bo
 * @date 2021/2/25 15:10
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class FillBlanksProblemBo extends AbstractProblemBo {

    private Long problemId;

    private Integer problemType;

    private Double score;

    private List<Blank> blanks;

    private String body;

    private Boolean hasRemark;

    private String remark;

    @Override
    public void assembleData() {
        this.problemId = super.getProblemId();
        this.problemType = super.getProblemType();
        JSONObject content = super.getContent();
        this.score = content.getDouble("score");
        JSONArray blanks = content.getJSONArray("blanks");
        this.blanks = JSON.parseArray(blanks.toJSONString(), Blank.class);
        this.body = content.getString("body");
        this.hasRemark = content.getBoolean("has_remark");
        this.remark = content.getString("remark");
    }

    @Override
    public ResponseCode validData() {
        if (score == null || score == 0) {
            return ResponseCode.PROBLEM_SCORE_INCORRECT;
        }

        if (CollectionUtils.isEmpty(blanks)) {
            return ResponseCode.PROBLEM_BLANKS_INCORRECT;
        }

        for (Blank blank : blanks) {
            if (blank.caseSensitive == null) {
                return ResponseCode.PROBLEM_CASE_SENSITIVE_INCORRECT;
            }

            if (blank.fuzzyMatch == null) {
                return ResponseCode.PROBLEM_FUZZY_MATCH_INCORRECT;
            }

            if (CollectionUtils.isEmpty(blank.answers)) {
                return ResponseCode.PROBLEM_BLANKS_ANSWER_INCORRECT;
            }

            if (blank.score == null || blank.score == 0) {
                return ResponseCode.PROBLEM_BLANKS_SCORE_INCORRECT;
            }
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

    @Data
    static class Blank {

        @JSONField(name = "case_sensitive")
        private Boolean caseSensitive;

        @JSONField(name = "fuzzy_match")
        private Boolean fuzzyMatch;

        private List<String> answers;

        private Double score;
    }
}
