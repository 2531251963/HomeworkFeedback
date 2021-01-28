package com.example.demo.controller;

import com.example.demo.common.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 **/
@RestController
public class HealthController {

    @RequestMapping("/health")
    public Response<String> health(){
        return Response.ok();
    }

}
