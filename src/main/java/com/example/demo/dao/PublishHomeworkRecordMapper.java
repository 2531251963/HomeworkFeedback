package com.example.demo.dao;

import com.example.demo.dao.po.PublishHomeworkRecordPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @description 作业发布记录
 * @date 2021/3/2 18:42
 **/
public interface PublishHomeworkRecordMapper {

    @Insert(value = "insert into publish_homework_record " +
            "(homework_id,class_ids,publisher_id,deadline_time,publish_time) " +
            "values (#{homeworkId},#{classIds},#{publisherId},#{deadlineTime},#{publishTime})")
    @Options(useGeneratedKeys = true, keyProperty = "publishId")
    void insertPublishHomeworkRecord(PublishHomeworkRecordPo publishHomeworkRecordPo);
}
