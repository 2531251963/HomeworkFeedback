package com.example.demo.dao.po;

import lombok.Data;

/**
 * @description 班级与用户关系po
 * @date 2021/2/10 19:37
 **/
@Data
public class ClassUserPo {

    private Integer classId;

    private Long userId;

    private Integer relationType;
}
