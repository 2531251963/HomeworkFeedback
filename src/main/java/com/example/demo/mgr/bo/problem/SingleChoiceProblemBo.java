package com.example.demo.mgr.bo.problem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.response.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description 单选题bo
 * @date 2021/2/24 15:12
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SingleChoiceProblemBo extends AbstractProblemBo {

    private Long problemId;

    private Integer problemType;

    private Double score;

    private List<ChoiceOptions> options;

    private String body;

    private Boolean hasRemark;

    private String remark;

    private List<String> answer;


    @Override
    public void assembleData(){
        this.problemId= super.getProblemId();
        this.problemType = super.getProblemType();
        JSONObject content = super.getContent();
        this.score = content.getDouble("score");
        JSONArray options = content.getJSONArray("options");
        this.options=JSON.parseArray(options.toJSONString(),ChoiceOptions.class);
        this.body=content.getString("body");
        this.hasRemark=content.getBoolean("has_remark");
        this.remark=content.getString("remark");
        JSONArray answer = content.getJSONArray("answer");
        this.answer=JSON.parseArray(answer.toJSONString(),String.class);
    }

    @Override
    public ResponseCode validData() {
        if (score == null || score == 0) {
            return ResponseCode.PROBLEM_SCORE_INCORRECT;
        }

        if (CollectionUtils.isEmpty(options) || options.size() < 2) {
            return ResponseCode.PROBLEM_OPTION_INCORRECT;
        }

        if (StringUtils.isBlank(body)){
            return ResponseCode.PROBLEM_BODY_INCORRECT;
        }

        if (hasRemark==null){
            return ResponseCode.PROBLEM_HAS_REMARK_INCORRECT;
        }

        if (hasRemark){
            if (StringUtils.isEmpty(remark)){
                return ResponseCode.PROBLEM_REMARK_INCORRECT;
            }
        }

        if (CollectionUtils.isEmpty(answer) || answer.size() != 1) {
            return ResponseCode.PROBLEM_ANSWER_INCORRECT;
        }
        return ResponseCode.OK;
    }

}
