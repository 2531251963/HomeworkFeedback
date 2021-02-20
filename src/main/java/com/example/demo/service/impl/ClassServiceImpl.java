package com.example.demo.service.impl;

import com.example.demo.mgr.ClassUserMgr;
import com.example.demo.mgr.bo.ClassBo;
import com.example.demo.service.ClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 班级相关service
 * @date 2021/2/20 14:45
 **/
@Service("classServiceImpl")
public class ClassServiceImpl implements ClassService {

    @Resource
    ClassUserMgr classUserMgr;
    @Override
    public List<ClassBo> getClassBoList(List<Integer> classIds) {
        return classUserMgr.getClassBoListByClassIds(classIds);
    }
}
