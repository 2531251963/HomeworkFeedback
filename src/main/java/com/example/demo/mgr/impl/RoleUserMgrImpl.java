package com.example.demo.mgr.impl;

import com.example.demo.dao.RoleMapper;
import com.example.demo.dao.RoleUserMapper;
import com.example.demo.dao.po.RolePo;
import com.example.demo.dao.po.RoleUserPo;
import com.example.demo.mgr.RoleUserMgr;
import com.example.demo.mgr.bo.RoleBo;
import com.example.demo.mgr.bo.RoleUserBo;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description 用户与角色mgr
 * @date 2021/1/28 17:46
 **/
@Repository
public class RoleUserMgrImpl implements RoleUserMgr {

    @Resource
    RoleMapper roleMapper;
    @Resource
    RoleUserMapper roleUserMapper;

    /**
     * 角色bo list
     */
    private List<RoleBo> roleBoList;

    /**
    * 根据角色名获取角色
    */
    private Map<String,RoleBo> roleBoMapByRoleName;

    /**
     * 根据角色id获取角色
     */
    private Map<Integer,RoleBo> roleBoMapByRoleId;

    @PostConstruct
    public void init() {
        roleBoList = getAllRole();
        roleBoMapByRoleName = roleBoList.stream().collect(Collectors.toMap(RoleBo::getRoleName, x -> x, (x1, x2) -> x1));
        roleBoMapByRoleId = roleBoList.stream().collect(Collectors.toMap(RoleBo::getRoleId, x -> x, (x1, x2) -> x1));
    }
    @Override
    public List<RoleBo> getAllRole() {
        List<RolePo> allRole = roleMapper.getAllRole();
        RoleBo roleBo=new RoleBo();
        List<RoleBo> roleBoList = allRole.stream().map(roleBo::convertFromRolePo).collect(Collectors.toList());
        return roleBoList;
    }

    @Override
    public RoleBo getRoleByRoleName(String name) {
        return roleBoMapByRoleName.get(name);
    }

    @Override
    public RoleBo getRoleByRoleId(Integer roleId) {
        return roleBoMapByRoleId.get(roleId);
    }

    @Override
    public RoleUserBo getRoleUserBoByUserId(Long userId) {
        List<RoleUserPo> roleUserPoList = roleUserMapper.getRoleUserPoByUserId(userId);
        List<RoleBo> roleBoList=new LinkedList<>();
        roleUserPoList.forEach(x->{
            roleBoList.add(getRoleByRoleId(x.getRoleId()));
        });
        return new RoleUserBo(userId,roleBoList);
    }

}
