package com.example.demo.mgr.impl;

import com.example.demo.dao.ClassMapper;
import com.example.demo.dao.ClassUserMapper;
import com.example.demo.dao.po.ClassPo;
import com.example.demo.dao.po.ClassUserPo;
import com.example.demo.mgr.ClassUserMgr;
import com.example.demo.mgr.bo.ClassBo;
import com.example.demo.mgr.bo.ClassUserBo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @date 2021/2/10 19:44
 **/
@Repository
public class ClassUserMgrImpl implements ClassUserMgr {

    @Resource
    ClassMapper classMapper;
    @Resource
    ClassUserMapper classUserMapper;
    @Override
    public List<ClassUserBo> getClassUserByUserId(Long userId) {
        List<ClassUserPo> classUserPos = classUserMapper.getClassUserByUserId(userId);
        ClassUserBo classUserBo=new ClassUserBo();
        List<ClassUserBo> classUserBos = classUserPos.stream().map(classUserBo::convertFromClassUserPo).collect(Collectors.toList());
        return classUserBos;
    }

    @Override
    public List<ClassUserBo> getClassUserByClassId(Integer classId) {
        List<ClassUserPo> classUserPos = classUserMapper.getClassUserByClassId(classId);
        ClassUserBo classUserBo=new ClassUserBo();
        List<ClassUserBo> classUserBos = classUserPos.stream().map(classUserBo::convertFromClassUserPo).collect(Collectors.toList());
        return classUserBos;
    }

    @Override
    public ClassBo getClassBoByClassId(Integer classId) {
        ClassPo classPo = classMapper.getClassById(classId);
        List<ClassUserBo> classUserBos = getClassUserByClassId(classId);
        ClassBo classBo=new ClassBo();
        return classBo.convertFromClassPo(classPo,classUserBos);
    }

    @Override
    public List<ClassBo> getClassBoListByClassIds(List<Integer> classIds) {
        List<ClassPo> classPos = classMapper.getClassListByIds(classIds);
        List<ClassBo> classBoList=new LinkedList<>();
        ClassBo classBo=new ClassBo();
        classPos.forEach(x->{
            List<ClassUserBo> classUserBos = getClassUserByClassId(x.getClassId());
            classBoList.add(classBo.convertFromClassPo(x, classUserBos));
        });
        return classBoList;
    }
}
