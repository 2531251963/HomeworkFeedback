package com.example.demo.dao.po;

import lombok.Data;

/**
 * User
 **/
@Data
public class UserPo {

    /**
     * 用户userId
     */
    private Long userId;

    /**
     * 帐号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

}
