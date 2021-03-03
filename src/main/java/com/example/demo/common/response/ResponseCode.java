package com.example.demo.common.response;

import lombok.Getter;

/**
 * 状态码
 **/
public enum ResponseCode {

    /**
     * ok
     */
    OK(0, "ok"),
    /**
     * 验证失败,帐号或密码错误
     */
    VALIDATION_FAILED(1001, "验证失败,帐号或密码错误"),

    /**
     * 权限验证失败,请重新登录
     */
    AUTH_VALIDATION_FAILED(1002, "权限验证失败,请重新登录"),

    /**
     * 权限验证过期,请重新登录
     */
    AUTH_VALIDATION_EXPIRE_FAILED(1003, "权限验证过期,请重新登录"),

    /**
     * 用户信息不存在
     */
    USER_INFO_NOT_EXIST(1004, "用户信息不存在"),

    /**
     * 题目分数数据不正确
     */
    PROBLEM_SCORE_INCORRECT(1200, "题目分数数据不正确"),

    /**
     * 题目部分分数数据不正确
     */
    PROBLEM_HALF_SCORE_INCORRECT(1200, "题目部分分数数据不正确"),

    /**
     * 题目选项数据不正确
     */
    PROBLEM_OPTION_INCORRECT(1200, "题目选项数据不正确"),

    /**
     * 题目选项规则数据不正确
     */
    PROBLEM_OPTION_RULES_INCORRECT(1200, "题目选项规则数据不正确"),

    /**
     * 题目题干数据不能为空
     */
    PROBLEM_BODY_INCORRECT(1200, "题目题干数据不能为空"),

    /**
     * 题目是否需要备注数据不正确
     */
    PROBLEM_HAS_REMARK_INCORRECT(1200, "题目是否需要备注数据不正确"),

    /**
     * 题目备注不能为空
     */
    PROBLEM_REMARK_INCORRECT(1200, "题目备注不能为空"),

    /**
     * 题目答案不能为空
     */
    PROBLEM_ANSWER_INCORRECT(1200, "题目答案不能为空"),

    /**
     * 题目填空答案是否模糊匹配数据不正确
     */
    PROBLEM_BLANKS_INCORRECT(1200, "题目填空项不能为空"),

    /**
     * 题目填空答案是否模糊匹配数据不正确
     */
    PROBLEM_CASE_SENSITIVE_INCORRECT(1200, "题目填空答案是否模糊匹配数据不正确"),

    /**
     * 题目填空答案是否区分大小写数据不正确
     */
    PROBLEM_FUZZY_MATCH_INCORRECT(1200, "题目填空答案是否区分大小写数据不正确"),

    /**
     * 题目填空答案不能为空
     */
    PROBLEM_BLANKS_ANSWER_INCORRECT(1200, "题目填空答案不能为空"),

    /**
     * 题目填空答案不能为空
     */
    PROBLEM_BLANKS_SCORE_INCORRECT(1200, "题目填空分数不能为空");

    @Getter
    private int code;
    @Getter
    private String msg;

    ResponseCode(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

}
