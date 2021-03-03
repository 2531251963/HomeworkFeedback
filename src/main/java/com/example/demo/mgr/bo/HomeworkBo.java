package com.example.demo.mgr.bo;

import com.example.demo.common.util.StringCaseUtil;
import com.example.demo.dao.po.HomeworkPo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description HomeworkBo
 * @date 2021/2/22 14:23
 **/
@Data
@Accessors(chain = true)
public class HomeworkBo {

    private Long homeworkId;

    private String homeworkName;

    private String homeworkNotice;

    private List<Long> problemIds;

    private Long creatorId;

    private String lastModifiedTime;

    public HomeworkBo convertFromHomeworkPo(HomeworkPo homeworkPo){
        return new HomeworkBo().setHomeworkId(homeworkPo.getHomeworkId()).setHomeworkName(homeworkPo.getHomeworkName())
                .setHomeworkNotice(homeworkPo.getHomeworkNotice()).setProblemIds(StringCaseUtil.stringSplitList(homeworkPo.getProblemIds()))
                .setCreatorId(homeworkPo.getCreatorId()).setLastModifiedTime(homeworkPo.getLastModifiedTime());
    }

}
