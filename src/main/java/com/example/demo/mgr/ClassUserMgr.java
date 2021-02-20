package com.example.demo.mgr;

import com.example.demo.mgr.bo.ClassBo;
import com.example.demo.mgr.bo.ClassUserBo;

import java.util.List;

/**
 * @description 班级与用户关系mgr
 * @date 2021/2/10 19:39
 **/
public interface ClassUserMgr {

    /**
     * 根据 user_id 获取与其班级关系
     */
    List<ClassUserBo> getClassUserByUserId(Long userId);

    /**
     * 根据class_id 获取与其所有用户关系
     */
    List<ClassUserBo> getClassUserByClassId(Integer classId);

    /**
     * 根据 根据class_id 获取班级信息
     */
    ClassBo getClassBoByClassId(Integer classId);

    /**
     * 根据 根据class_ids 获取班级列表信息
     */
    List<ClassBo> getClassBoListByClassIds(List<Integer> classIds);
}
