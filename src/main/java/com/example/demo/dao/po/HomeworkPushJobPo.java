package com.example.demo.dao.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description HomeworkPushJobPo
 * @date 2021/3/3 17:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkPushJobPo {

    private Long publishId;

    private String data;

    private String deadlineTime;

    private Integer status;
}
