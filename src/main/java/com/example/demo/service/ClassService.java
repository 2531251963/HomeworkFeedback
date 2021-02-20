package com.example.demo.service;

import com.example.demo.mgr.bo.ClassBo;

import java.util.List;

/**
 * @description 班级相关接口
 * @date 2021/2/20 14:41
 **/
public interface ClassService {

    /**
    * 获取班级信息列表
    */
    List<ClassBo> getClassBoList(List<Integer> classIds);
}
