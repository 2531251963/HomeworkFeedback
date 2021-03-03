package com.example.demo.dao;

import com.example.demo.dao.po.PublishClassRecordPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 作业发布与班级关系记录mapper
 * @date 2021/3/2 18:56
 **/
public interface PublishClassRecordMapper {

    @Insert("<script>"  +
            "insert into publish_class_record(publish_id,class_id) "
            + "values <foreach collection='list' item='publishClassItem' index='index' separator=','>" +
            "(#{publishClassItem.publishId},#{publishClassItem.classId})" +
            " </foreach>"
            + "</script>")
    void batchInsertPublishClassRecord(@Param("list") List<PublishClassRecordPo> list);
}
