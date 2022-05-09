package com.cet4.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cet4.pojo.po.UserPo;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserDao extends BaseMapper<UserPo> {

    UserPo login(@Param("username") String username, @Param("password") String password,@Param("flag")String flag);

    void save(@Param("list") List<UserPo> list);

    UserPo findUserByPhone(@Param("phone") String phone);

    UserPo findUserByEmail(@Param("email") String email);
    //根据邮箱修改登录时间
    @Update("update `user` set RECENTLY_LANDED=#{recentlyLanded} where EMAIL=#{email}")
    void updateRecentlyLandedByEmail(@Param("email") String email, @Param("recentlyLanded") String recentlyLanded);
    //根据手机号码修改登录时间
    @Update("update `user` set RECENTLY_LANDED=#{recentlyLanded} where EMAIL=#{phone}")
    void updateRecentlyLandedByPhone(@Param("phone")String phone,@Param("recentlyLanded")String recentlyLanded);
}
