package com.example.demo.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @description UpdateHomeworkDto
 * @date 2021/2/20 18:41
 **/
@Data
public class SaveHomeworkDto {

    @NotNull(message = "作业id不能为空")
    private Long homeworkId;

    @Size(min = 1,message = "题目ids最少为1")
    private List<Long> problemIds;
}
