package com.example.demo.mgr;

import com.example.demo.mgr.bo.RoleBo;
import com.example.demo.mgr.bo.RoleUserBo;

import java.util.List;

/**
 *
 * 用户与角色mgr
 **/
public interface RoleUserMgr {

    /**
     *  获取所有角色字典
     */
    List<RoleBo> getAllRole ();

    /**
     *   根据角色名获取角色
     */
    RoleBo getRoleByRoleName (String name);

    /**
     *   根据角色id获取角色
     */
    RoleBo getRoleByRoleId (Integer roleId);

    /**
     *   根据用户id获取用户角色关系
     */
    RoleUserBo getRoleUserBoByUserId(Long userId);
}
