package com.example.demo.controller.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description AbstractProblemVo
 * @date 2021/3/1 15:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemVo {

    private Long problemId;

    private Integer problemType;

    private JSONObject content;
}
