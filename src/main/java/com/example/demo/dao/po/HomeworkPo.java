package com.example.demo.dao.po;

import lombok.Data;

/**
 * @description HomeworkPo
 * @date 2021/2/20 17:05
 **/
@Data
public class HomeworkPo {

    private Long homeworkId;

    private String homeworkName;

    private String homeworkNotice;

    private String problemIds;

    private Long creatorId;

    private String lastModifiedTime;
}
