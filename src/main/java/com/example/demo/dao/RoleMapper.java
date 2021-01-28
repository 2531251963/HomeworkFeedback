package com.example.demo.dao;

import com.example.demo.dao.po.RolePo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description 角色
 * @date 2021/1/28 17:36
 **/
public interface RoleMapper {

    /**
     * 获取角色字典
     */
    @Select("select * from role")
    List<RolePo> getAllRole();
}
