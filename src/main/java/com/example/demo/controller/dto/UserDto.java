package com.example.demo.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 **/
@Data
public class UserDto {

    @NotBlank(message = "帐号不能为空")
    private  String account;

    @NotBlank(message = "密码不能为空")
    private String password;

}
