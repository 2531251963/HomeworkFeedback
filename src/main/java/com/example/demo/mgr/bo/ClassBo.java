package com.example.demo.mgr.bo;

import com.example.demo.dao.po.ClassPo;
import com.example.demo.dao.po.ClassUserPo;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @description ClassBo
 * @date 2021/2/20 14:31
 **/
@Data
public class ClassBo {

    private Integer classId;

    private String className;

    private List<Long> teacherUserIds;

    private List<Long> studentUserIds;

    public ClassBo convertFromClassPo(ClassPo classPo,List<ClassUserBo> classUserBoList) {
        ClassBo classBo = new ClassBo();
        classBo.setClassId(classPo.getClassId());
        classBo.setClassName(classPo.getClassName());
        List<Long> teacherUserIds = new LinkedList<>();
        List<Long> studentUserIds = new LinkedList<>();
        classUserBoList.forEach(x -> {
            if (x.isStudentInClass()) {
                studentUserIds.add(x.getUserId());
            } else if (x.isTeacherInClass()) {
                teacherUserIds.add(x.getUserId());
            }
        });
        classBo.setTeacherUserIds(teacherUserIds);
        classBo.setStudentUserIds(studentUserIds);
        return classBo;
    }
}
