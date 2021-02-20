package com.example.demo.controller;

import com.example.demo.common.response.Response;
import com.example.demo.common.response.ResponseCode;
import com.example.demo.common.util.JwtUtil;
import com.example.demo.controller.dto.ClassInfoListDto;
import com.example.demo.controller.vo.ClassInfoVo;
import com.example.demo.controller.vo.Token;
import com.example.demo.mgr.bo.ClassBo;
import com.example.demo.mgr.bo.UserBo;
import com.example.demo.service.ClassService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description 班级相关controller
 * @date 2021/2/20 11:31
 **/
@RestController()
@RequestMapping("/class")
public class ClassController {

    @Resource(name = "classServiceImpl")
    ClassService classService;
    @Resource(name = "userServiceImpl")
    UserService userService;

    @PostMapping("/info_list")
    public Response<List<ClassInfoVo>> infoList(@Valid @RequestBody ClassInfoListDto classInfoListDto) {
        List<ClassBo> classBoList = classService.getClassBoList(classInfoListDto.getClassIds());
        List<ClassInfoVo> classInfoVoList = new LinkedList<>();
        classBoList.forEach(x -> {
            ClassInfoVo classInfoVo = new ClassInfoVo();
            classInfoVo.assembleFromClassBo(x);
            if (classInfoListDto.isHasClassUserInfo()) {
                List<UserBo> teacherUserBos = x.getTeacherUserIds().stream().map(userId -> userService.getUserInfo(userId)).collect(Collectors.toList());
                List<UserBo> studentUserBos = x.getStudentUserIds().stream().map(userId -> userService.getUserInfo(userId)).collect(Collectors.toList());
                classInfoVo.assembleFromUserBoList(teacherUserBos,studentUserBos);
            }
            classInfoVoList.add(classInfoVo);
        });
        return Response.ok(classInfoVoList);
    }
}
