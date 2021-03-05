package com.example.demo.mgr.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description UserHomeworkDetailBo
 * @date 2021/3/5 14:39
 **/
@Data
@NoArgsConstructor
public class UserHomeworkDetailBo {

    private Long detailId;

    private Long publishId;

    private Long homeworkId;

    private Long userId;

    private String status;

    private List<UserHomeworkAnswerBo> answers;

    private Double score;

    private String doneHomeworkJson;

    public UserHomeworkDetailBo(Long publishId, Long homeworkId, Long userId, String status) {
        this.publishId = publishId;
        this.homeworkId = homeworkId;
        this.userId = userId;
        this.status = status;
    }
}
