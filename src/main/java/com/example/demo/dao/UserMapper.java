package com.example.demo.dao;


import com.example.demo.dao.po.UserPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户
 */
public interface UserMapper {

    int insert(UserPo user);

    int updateByLoginName(UserPo user);

    /**
     * 根据帐号,密码 获取用户
     */
    @Select("select * from user where account=#{account} and password=#{password}")
    UserPo getUserByAccount(@Param("account") String account, @Param("password") String password);

    /**
     * 根据userId 获取用户
     */
    @Select("select * from user where user_id=#{userId}")
    UserPo getUserByUserId(@Param("userId") Long userId);
}
