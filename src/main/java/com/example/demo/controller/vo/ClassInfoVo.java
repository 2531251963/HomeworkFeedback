package com.example.demo.controller.vo;

import com.example.demo.mgr.bo.ClassBo;
import com.example.demo.mgr.bo.UserBo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @className ClassInfoVo
 * @date 2021/2/20 14:57
 **/
@Data
public class ClassInfoVo {

    private Integer classId;

    private String className;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClassUserInfo classUserInfo;

    @Data
    static class ClassUserInfo{

        private List<UserVo> teacherInfo;

        private List<UserVo> studentInfo;
    }



    public void assembleFromClassBo(ClassBo classBo){
        setClassId(classBo.getClassId());
        setClassName(classBo.getClassName());
    }

    public void assembleFromUserBoList(List<UserBo> teacherUserBos,List<UserBo> studentUserBos){
        ClassUserInfo classUserInfo=new ClassUserInfo();
        List<UserVo> teacherUserVos = teacherUserBos.stream().map(x -> new UserVo().convertFromBo(x)).collect(Collectors.toList());
        List<UserVo> studentUserVos = studentUserBos.stream().map(x -> new UserVo().convertFromBo(x)).collect(Collectors.toList());
        classUserInfo.setTeacherInfo(teacherUserVos);
        classUserInfo.setStudentInfo(studentUserVos);
        setClassUserInfo(classUserInfo);
    }
}

