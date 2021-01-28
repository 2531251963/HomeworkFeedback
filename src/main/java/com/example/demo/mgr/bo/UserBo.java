package com.example.demo.mgr.bo;


import com.example.demo.dao.po.UserPo;
import lombok.Data;

@Data
public class UserBo {

    private Long userId;

    private String userName;

    private String email;

    private RoleUserBo roleUserBo;


    public  UserBo convertFromUserPoUserId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    public  UserBo convertFromUserPo(UserPo user) {
        this.setUserId(user.getUserId());
        this.setUserName(user.getUserName());
        this.setEmail(user.getEmail());
        return this;
    }

    public void assembleRoleUserBo(RoleUserBo roleUserBo) {
        this.setRoleUserBo(roleUserBo);
    }
}
