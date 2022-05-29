package com.cet4.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cet4.pojo.po.UserPo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;


@Mapper
public interface UserDao extends BaseMapper<UserPo> {

    List<UserPo> findAll(@Param("flag")Integer flag,@Param("vague") String vague);

    UserPo login(@Param("username") String username, @Param("password") String password,@Param("flag")String flag);

    void save(@Param("list") List<UserPo> list);

    UserPo findUserByPhone(@Param("phone") String phone);

    UserPo findUserByEmail(@Param("email") String email);
    //根据邮箱修改登录时间
    void updateRecentlyLandedByEmail(@Param("email") String email, @Param("recentlyLanded") String recentlyLanded);
    //根据手机号码修改登录时间
    void updateRecentlyLandedByPhone(@Param("phone")String phone,@Param("recentlyLanded")String recentlyLanded);
}
