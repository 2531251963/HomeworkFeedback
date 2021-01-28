package com.example.demo.dao;

import com.example.demo.dao.po.RoleUserPo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description 用户与角色关系
 * @date 2021/1/28 18:31
 **/
public interface RoleUserMapper {

    /**
     * 根据userId 获取用户角色
     */
    @Select("select * from role_user")
   List<RoleUserPo> getRoleUserPoByUserId(Long userId);
}
