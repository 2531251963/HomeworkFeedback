package com.example.demo.mgr.bo;

import lombok.Data;

import java.util.List;

/**
 * @description 用户填写作业答案
 * @date 2021/3/5 14:52
 **/
@Data
public class UserHomeworkAnswerBo {

    private List<String> choice;

    private List<List<String>> blanks;

    private String subjective;
}
