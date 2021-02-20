package com.example.demo.controller.vo;

import com.example.demo.mgr.bo.ClassUserBo;
import com.example.demo.mgr.bo.RoleBo;
import com.example.demo.mgr.bo.UserBo;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 **/
@Data
public class UserVo {

    private String userName;

    private String email;

    private List<String> roleNames;

    private List<Integer> teachClassIds;

    private List<Integer> studyClassIds;

    public  UserVo convertFromBo(UserBo userBo){
        this.setUserName(userBo.getUserName());
        this.setEmail(userBo.getEmail());
        List<RoleBo> roleBoList = userBo.getRoleUserBo().getRoleBoList();
        this.setRoleNames(roleBoList.stream().map(RoleBo::getRoleName).collect(Collectors.toList()));
        this.setStudyClassIds(userBo.getStudyClassIds());
        this.setTeachClassIds(userBo.getTeachClassIds());
        return this;
    }


}
