package com.example.demo.mgr.bo;

import com.example.demo.dao.po.ClassUserPo;
import lombok.Data;

/**
 * @description 班级与用户关系bo
 * @date 2021/2/10 19:42
 **/
@Data
public class ClassUserBo {

    private Integer classId;

    private Long userId;

    private Integer relationType;

    public ClassUserBo convertFromClassUserPo(ClassUserPo classUserPo) {
        ClassUserBo classUserBo = new ClassUserBo();
        classUserBo.setClassId(classUserPo.getClassId());
        classUserBo.setUserId(classUserPo.getUserId());
        classUserBo.setRelationType(classUserPo.getRelationType());
        return classUserBo;
    }

    public boolean isStudentInClass() {
        if (relationType == 0) {
            return true;
        }
        return false;
    }

    public boolean isTeacherInClass() {
        if (relationType == 1) {
            return true;
        }
        return false;
    }
}
