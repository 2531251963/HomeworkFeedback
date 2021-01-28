package com.example.demo.mgr;

import com.example.demo.mgr.bo.UserBo;

/**
 **/

public interface UserMgr {

    /**
    * 根据帐号密码获取用户信息
    */
    UserBo getUserInfoByAccount(String account, String password);

    /**
     * 根据userId获取用户信息
     */
    UserBo getUserInfoByUserId(Long userId);
}
