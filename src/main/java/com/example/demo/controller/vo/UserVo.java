package com.example.demo.controller.vo;

import com.example.demo.mgr.bo.UserBo;
import lombok.Data;

/**
 **/
@Data
public class UserVo {

    private String userName;

    private String email;

    public  UserVo convertFromBo(UserBo userBo){
        this.setUserName(userBo.getUserName());
        this.setEmail(userBo.getEmail());
        return this;
    }


}
