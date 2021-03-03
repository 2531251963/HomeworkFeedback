package com.example.demo.dao;

import com.example.demo.dao.po.HomeworkPo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description HomeworkMapper
 * @date 2021/2/20 16:44
 **/
public interface HomeworkMapper {

    @Insert(value = "insert into homework (homework_name,homework_notice,creator_id,last_modified_time) " +
            "values (#{homeworkName},#{homeworkNotice},#{creatorId},#{lastModifiedTime})")
    @Options(useGeneratedKeys = true, keyProperty = "homeworkId")
    void createHomework(HomeworkPo homeworkPo);

    @Update(value = "update homework set problem_ids=#{problemIds},last_modified_time=#{lastModifiedTime} " +
            "where homework_id=#{homeworkId}")
    boolean updateProblemIds(HomeworkPo homeworkPo);

    @Select(value = "select * from homework where creator_id=#{creatorId}")
    List<HomeworkPo> selectHomeworkListByCreatorId(@Param("creatorId") Long creatorId);


    @Select(value = "select * from homework where homework_id=#{homeworkId}")
    HomeworkPo selectHomeworkListByhomeworkId(@Param("homeworkId") Long homeworkId);
}
