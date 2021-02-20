package com.example.demo.service.impl;

import com.example.demo.mgr.ClassUserMgr;
import com.example.demo.mgr.RoleUserMgr;
import com.example.demo.mgr.UserMgr;
import com.example.demo.mgr.bo.ClassUserBo;
import com.example.demo.mgr.bo.RoleUserBo;
import com.example.demo.mgr.bo.UserBo;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 **/
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Resource
    UserMgr userMgr;
    @Resource
    RoleUserMgr roleUserMgr;
    @Resource
    ClassUserMgr classUserMgr;
    @Override
    public UserBo login(String account, String password) {
        return userMgr.getUserInfoByAccount(account, password);
    }

    @Override
    public UserBo getUserInfo(Long userId) {
        UserBo userBo = userMgr.getUserInfoByUserId(userId);
        RoleUserBo roleUserBo = roleUserMgr.getRoleUserBoByUserId(userId);
        List<ClassUserBo> classUserBos = classUserMgr.getClassUserByUserId(userId);
        userBo.assembleRoleUserBo(roleUserBo);
        userBo.assembleClassUserBo(classUserBos);
        return userBo;
    }
}
