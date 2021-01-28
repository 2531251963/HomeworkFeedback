package com.example.demo.service.impl;

import com.example.demo.mgr.RoleUserMgr;
import com.example.demo.mgr.UserMgr;
import com.example.demo.mgr.bo.RoleUserBo;
import com.example.demo.mgr.bo.UserBo;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 **/
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Resource
    UserMgr userMgr;
    @Resource
    RoleUserMgr roleUserMgr;
    @Override
    public UserBo login(String account, String password) {
        return userMgr.getUserInfoByAccount(account, password);
    }

    @Override
    public UserBo getUserInfo(Long userId) {
        UserBo userBo = userMgr.getUserInfoByUserId(userId);
        RoleUserBo roleUserBo = roleUserMgr.getRoleUserBoByUserId(userBo.getUserId());
        userBo.assembleRoleUserBo(roleUserBo);
        return userBo;
    }
}
