package com.example.demo.dao;

import com.example.demo.dao.po.HomeworkPushJobPo;
import com.example.demo.dao.po.ProblemPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @description 作业通知任务Mapper
 * @date 2021/3/3 17:55
 **/
public interface HomeworkPushJobMapper {

    @Insert(value = "insert into homework_push_job (publish_id,data,deadline_time,status) " +
            "values (#{publishId},#{data},#{deadlineTime},#{status})")
    void insertHomeworkPushJobPo(HomeworkPushJobPo homeworkPushJobPo);

    /**
     * 查询大于当前时间且未通知的任务
     */
    @Select("select * from homework_push_job where deadline_time > unix_timestamp(NOW()) and status=2")
    List<HomeworkPushJobPo> selectUnPushHomeworkJob();

    /**
     * 更新作业通知任务的状态
     */
    @Update(value = "update homework_push_job set status= #{status} where publish_id =#{publishId}" )
    Boolean updateHomeworkPushJobPoStatus(HomeworkPushJobPo homeworkPushJobPo);
}
