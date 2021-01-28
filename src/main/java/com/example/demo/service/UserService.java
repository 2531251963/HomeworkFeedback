package com.example.demo.service;

import com.example.demo.mgr.bo.UserBo;

/**
 * 用户Service
 **/
public interface UserService {

    /**
    * 用户登录
    */
    UserBo login(String account, String password);

    /**
     * 获取用户信息
     */
    UserBo getUserInfo(Long userId);
}
