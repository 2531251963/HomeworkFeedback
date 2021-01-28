package com.example.demo.mgr.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description 用户角色bo
 * @date 2021/1/28 18:36
 **/
@Data
@NoArgsConstructor
public class RoleUserBo {

    private Long userId;

    private List<RoleBo> roleBoList;

    public RoleUserBo(Long userId, List<RoleBo> roleBoList) {
        this.userId = userId;
        this.roleBoList = roleBoList;
    }
}
