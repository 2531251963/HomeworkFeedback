package com.example.demo.mgr.bo;


import com.example.demo.dao.po.UserPo;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class UserBo {

    private Long userId;

    private String userName;

    private String email;

    private RoleUserBo roleUserBo;

    private List<Integer> teachClassIds;

    private List<Integer> studyClassIds;

    public UserBo convertFromUserPoUserId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    public UserBo convertFromUserPo(UserPo user) {
        this.setUserId(user.getUserId());
        this.setUserName(user.getUserName());
        this.setEmail(user.getEmail());
        return this;
    }

    public void assembleRoleUserBo(RoleUserBo roleUserBo) {
        this.setRoleUserBo(roleUserBo);
    }

    public void assembleClassUserBo(List<ClassUserBo> classUserBos) {
        teachClassIds = new LinkedList<>();
        studyClassIds = new LinkedList<>();
        classUserBos.forEach(x -> {
            if (x.isStudentInClass()) {
                studyClassIds.add(x.getClassId());
            } else if (x.isTeacherInClass()) {
                teachClassIds.add(x.getClassId());
            }
        });
    }
}
