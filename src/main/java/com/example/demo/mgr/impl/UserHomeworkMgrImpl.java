package com.example.demo.mgr.impl;

import com.example.demo.dao.UserHomeworkDetailMapper;
import com.example.demo.dao.po.UserHomeworkDetailPo;
import com.example.demo.mgr.UserHomeworkMgr;
import com.example.demo.mgr.bo.UserHomeworkDetailBo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description 用户作业操作MgrImpl
 * @date 2021/3/5 15:03
 **/
@Component
public class UserHomeworkMgrImpl implements UserHomeworkMgr {

    @Resource
    UserHomeworkDetailMapper userHomeworkDetailMapper;

    @Override
    public void batchSaveFirstHomeworkDetail(List<UserHomeworkDetailBo> userHomeworkDetailBo) {
        List<UserHomeworkDetailPo> userHomeworkDetailPos = userHomeworkDetailBo.stream().map(x -> new UserHomeworkDetailPo().convertFromBo(x)).collect(Collectors.toList());
        userHomeworkDetailMapper.batchInsertUserHomeworkDetailPo(userHomeworkDetailPos);
    }
}
