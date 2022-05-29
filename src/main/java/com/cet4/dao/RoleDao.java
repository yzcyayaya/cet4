package com.cet4.dao;

import com.cet4.pojo.po.RolePo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleDao {
    @Select("select a.ROLE_ID id,a.ROLE_NAME name from `role` a left join user_role b on a.ROLE_ID = b.ROLE_ID where USER_ID = #{userId}")
    List<RolePo> findAllByUserId(@Param("userId")String userId);

    @Insert("insert into user_role (USER_ID,ROLE_ID)values(#{userId},#{roleId})")
    void insertRoleByUserId(@Param("userId")String userId,@Param("roleId")Integer roleId);

    @Delete("<script>" +
            "DELETE  FROM user_role WHERE USER_ID IN " +
            "<foreach collection='list' index='index' item='item' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    void deleteRoleByUserId(@Param("list")List<String> userId);
}
