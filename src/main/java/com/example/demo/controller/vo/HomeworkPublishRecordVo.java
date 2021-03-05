package com.example.demo.controller.vo;

import lombok.Data;

/**
 * @description 作业发布记录Vo
 * @date 2021/3/5 18:52
 **/
@Data
public class HomeworkPublishRecordVo {

    private Long publishId;

    private LibraryHomeworkVo homework;

    private String deadlineTime;

    private String publishTime;
}
