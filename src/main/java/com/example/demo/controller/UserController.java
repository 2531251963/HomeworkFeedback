package com.example.demo.controller;


import com.example.demo.common.response.Response;
import com.example.demo.common.response.ResponseCode;
import com.example.demo.common.util.JwtUtil;
import com.example.demo.controller.dto.UserDto;
import com.example.demo.controller.vo.Token;
import com.example.demo.controller.vo.UserVo;
import com.example.demo.mgr.bo.UserBo;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户接口
 */
@RestController()
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userServiceImpl")
    UserService userService;

    @PostMapping("/login")
    public Response<Token> login(@RequestBody UserDto userDto) {
        UserBo userBo = userService.login(userDto.getAccount(), userDto.getPassword());
        if (userBo == null || userBo.getUserId() == null) {
            return Response.error(ResponseCode.VALIDATION_FAILED);
        }
        return Response.ok(new Token(JwtUtil.buildJwt(userBo.getUserId())));
    }

    @GetMapping("/info")
    public Response<UserVo> getUserInfo(@RequestHeader(value = "token") String token) {
        Long userId = JwtUtil.getUserId(token);
        UserBo userBo = userService.getUserInfo(userId);
        if (userBo == null || userBo.getUserId() == null) {
            return Response.error(ResponseCode.USER_INFO_NOT_EXIST);
        }
        UserVo userVo = new UserVo().convertFromBo(userBo);
        return Response.ok(userVo);
    }
}
