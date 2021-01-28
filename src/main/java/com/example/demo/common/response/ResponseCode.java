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
    USER_INFO_NOT_EXIST(1004, "用户信息不存在");
    @Getter
    private int code;
    @Getter
    private String msg;

    ResponseCode(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

}
