package com.example.demo.dao;

import com.example.demo.dao.po.PublishClassRecordPo;
import com.example.demo.dao.po.UserHomeworkDetailPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description 用户作业细节情况数据Mapper
 * @date 2021/3/5 11:41
 **/
public interface UserHomeworkDetailMapper {

    /**
     * 查询作业状态
    */
    @Select("select status from user_homework_detail where publish_id=#{publishId} and user_id=#{userId}")
    UserHomeworkDetailPo getUserHomeworkStatus(@Param("publishId") Long publishId,@Param("userId") Long userId);

    @Insert("<script>"  +
            "insert into user_homework_detail(publish_id,homework_id,user_id,status,answers,score,done_homework_json) "
            + "values <foreach collection='list' item='item' index='index' separator=','>" +
            "(#{item.publishId},#{item.homeworkId},#{item.userId},#{item.status},#{item.answers},#{item.score},#{item.doneHomeworkJson})" +
            " </foreach>"
            + "</script>")
    void batchInsertUserHomeworkDetailPo(@Param("list") List<UserHomeworkDetailPo> list);
}
