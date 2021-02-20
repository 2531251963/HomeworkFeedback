package com.example.demo.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @description CreateHomeworkDto
 * @date 2021/2/20 16:32
 **/
@Data
public class CreateHomeworkDto {

    @NotBlank(message = "作业名称不能为空")
    private String homeworkName;

    @NotBlank(message = "作业须知不能为空")
    private String homeworkNotice;
}
