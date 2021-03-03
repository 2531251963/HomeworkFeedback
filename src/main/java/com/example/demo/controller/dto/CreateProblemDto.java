package com.example.demo.controller.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @description CreateProblemDto
 * @date 2021/2/24 14:47
 **/
@Data
public class CreateProblemDto {

    private Long problemId;

    @NotNull(message = "题目类型不能为空")
    @Range(min = 1,max = 4,message = "题目类型不存在")
    private Integer problemType;

    @NotNull(message = "题目内容不能为空")
    private JSONObject content;


}
