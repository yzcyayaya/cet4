package com.cet4.dao;

import com.cet4.pojo.po.RolePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleDao {
    @Select("select a.ROLE_ID id,a.ROLE_NAME name from `role` a left join user_role b on a.ROLE_ID = b.ROLE_ID where USER_ID = #{userId}")
    List<RolePo> findAllByUserId(@Param("userId")String userId);
}
