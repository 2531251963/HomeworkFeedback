package com.example.demo.dao;

import com.example.demo.dao.po.ClassPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description 班级mapper
 * @date 2021/2/10 19:31
 **/
public interface ClassMapper {

    /**
     * 根据class_id 获取班级信息
     */
    @Select("select * from class where class_id=#{class_id}")
    ClassPo getClassById(@Param("class_id") Integer classId);

    /**
     * 根据class_id 获取班级信息
     */
    @Select({"<script>" +
                    "select * from class where class_id in " +
                    "<foreach item = 'item' index = 'index' collection = 'class_ids' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
                    "</script>"})
    List<ClassPo> getClassListByIds(@Param("class_ids") List<Integer> classIds);
}
