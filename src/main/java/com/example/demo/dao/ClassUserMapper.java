package com.example.demo.dao;

import com.example.demo.dao.po.ClassUserPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description 班级与用户关系mapper
 * @date 2021/2/10 19:35
 **/
public interface ClassUserMapper {

    /**
     * 根据class_id 获取与其所有用户关系
     */
    @Select("select * from class_user where class_id=#{class_id}")
    List<ClassUserPo> getClassUserByClassId(@Param("class_id") Integer classId);

    /**
     * 根据user_id 获取与其班级关系
     */
    @Select("select * from class_user where user_id=#{user_id}")
    List<ClassUserPo>  getClassUserByUserId(@Param("user_id") Long userId);
}
