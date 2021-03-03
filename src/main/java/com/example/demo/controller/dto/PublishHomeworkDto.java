package com.example.demo.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @description 发布作业dto
 * @date 2021/3/2 17:25
 **/
@Data
public class PublishHomeworkDto {

    @NotNull(message = "作业id不能为空")
    private Long homeworkId;

    @NotNull(message = "班级ids不能为空")
    @Size(min = 1,message = "班级ids最少为1个")
    private List<Integer> classIds;

    @NotNull(message = "作业截止时间不能为空")
    private Long deadlineTime;
}
