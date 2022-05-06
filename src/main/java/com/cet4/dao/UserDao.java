package com.cet4.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cet4.pojo.po.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserDao extends BaseMapper<UserPo> {
    @Select("select * from user where username=#{username} and password=#{password}")
    UserPo login(@Param("username") String username, @Param("password") String password);
}
