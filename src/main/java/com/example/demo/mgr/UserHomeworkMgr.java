package com.example.demo.mgr;

import com.example.demo.mgr.bo.UserHomeworkDetailBo;

import java.util.List;

/**
 * @description 用户作业操作mgr
 * @date 2021/3/5 14:49
 **/
public interface UserHomeworkMgr {

    /**
    * 批量保存 用户作业详情（首次，在刚创建时）
    */
    void batchSaveFirstHomeworkDetail(List<UserHomeworkDetailBo> userHomeworkDetailBo);
}
