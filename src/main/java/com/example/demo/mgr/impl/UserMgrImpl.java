package com.example.demo.mgr.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.dao.po.UserPo;
import com.example.demo.mgr.HomeworkMailSenderMgr;
import com.example.demo.mgr.UserMgr;
import com.example.demo.mgr.bo.UserBo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


/**
 *
 **/
@Repository
public class UserMgrImpl implements UserMgr {


    @Resource
    UserMapper userMapper;
    @Override
    public UserBo getUserInfoByAccount(String account, String password) {
        UserPo userPo = userMapper.getUserByAccount(account, password);
        UserBo userBo=new UserBo();
        if (userPo ==null){
            return userBo;
        }
        return userBo.convertFromUserPoUserId(userPo.getUserId());
    }

    @Override
    public UserBo getUserInfoByUserId(Long userId) {
        UserPo userPo = userMapper.getUserByUserId(userId);
        UserBo userBo=new UserBo();
        if (userPo ==null){
            return userBo;
        }
        return userBo.convertFromUserPo(userPo);
    }
}
