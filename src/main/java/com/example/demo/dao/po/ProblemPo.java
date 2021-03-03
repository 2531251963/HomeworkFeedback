package com.example.demo.dao.po;

import lombok.Data;

/**
 * @description ProblemPo
 * @date 2021/2/24 17:46
 **/
@Data
public class ProblemPo {

    private Long problemId;

    private Integer problemType;

    private String content;

    private Long creatorId;

    private Integer available;
}
