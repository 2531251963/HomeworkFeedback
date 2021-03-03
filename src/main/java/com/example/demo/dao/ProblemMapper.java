package com.example.demo.dao;

import com.example.demo.dao.po.ProblemPo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description 题目Mapper
 * @date 2021/2/24 17:42
 **/
public interface ProblemMapper {

    @Insert(value = "insert into problem (problem_type,content,creator_id) " +
            "values (#{problemType},#{content},#{creatorId})")
    @Options(useGeneratedKeys = true, keyProperty = "problemId")
    void createProblem(ProblemPo problemPo);

    @Update(value = "update problem set content= #{content} where problem_id =#{problemId}" )
    Boolean updateProblemContent(ProblemPo problemPo);

    @Select({"<script>" +
            "select * from problem where problem_id in " +
            "<foreach item = 'item' index = 'index' collection = 'problem_ids' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>"})
    List<ProblemPo> selectProblemList(@Param("problem_ids")List<Long> problemIds);
}
