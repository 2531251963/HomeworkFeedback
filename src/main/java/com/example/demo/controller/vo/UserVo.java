package com.example.demo.controller.vo;

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

    public  UserVo convertFromBo(UserBo userBo){
        this.setUserName(userBo.getUserName());
        this.setEmail(userBo.getEmail());
        List<RoleBo> roleBoList = userBo.getRoleUserBo().getRoleBoList();
        roleNames = roleBoList.stream().map(RoleBo::getRoleName).collect(Collectors.toList());
        return this;
    }


}
