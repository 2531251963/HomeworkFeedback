package com.example.demo.controller;

import com.example.demo.common.response.Response;
import com.example.demo.common.util.JwtUtil;
import com.example.demo.controller.dto.CreateHomeworkDto;
import com.example.demo.controller.dto.SaveHomeworkDto;
import com.example.demo.service.HomeworkLibraryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @description 作业相关controller
 * @date 2021/2/20 16:30
 **/
@RestController()
@RequestMapping("/homework_library")
public class HomeworkLibraryController {

    @Resource(name = "homeworkLibraryServiceImpl")
    HomeworkLibraryService homeworkLibraryService;
    @PostMapping("/create_homework")
    public Response<Long> createHomework(@Valid  @RequestBody CreateHomeworkDto createHomeworkDto,@RequestHeader(value = "token") String token) {
        Long userId = JwtUtil.getUserId(token);
        Long homeworkId = homeworkLibraryService.createHomework(createHomeworkDto.getHomeworkName(), createHomeworkDto.getHomeworkNotice(),userId);
        return Response.ok(homeworkId);
    }

    @PostMapping("/save_homework")
    public Response<Boolean> createHomework(@Valid @RequestBody SaveHomeworkDto saveHomeworkDto) {
        boolean result = homeworkLibraryService.saveHomework(saveHomeworkDto.getHomeworkId(), saveHomeworkDto.getProblemIds());
        return Response.ok(result);
    }
}
