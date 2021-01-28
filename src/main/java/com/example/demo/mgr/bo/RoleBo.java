package com.example.demo.mgr.bo;

import com.example.demo.dao.po.RolePo;
import lombok.Data;

/**
 * 角色Bo
 **/
@Data
public class RoleBo {

    private Integer roleId;

    private String roleName;

    private String roleDesc;

    public RoleBo convertFromRolePo(RolePo rolePo) {
        RoleBo roleBo=new RoleBo();
        roleBo.setRoleId(rolePo.getRoleId());
        roleBo.setRoleName(rolePo.getRoleName());
        roleBo.setRoleDesc(rolePo.getRoleDesc());
        return roleBo;
    }

}
