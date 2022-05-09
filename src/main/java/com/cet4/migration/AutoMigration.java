package com.cet4.migration;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AutoMigration  {
    //创建用户基础信息表
    void createUserTable();
    //创建个人信息表
    void createPersonalInfoTable();
    //根据表名删除表
    void dropTableName(@Param("tableName") String tableName);
    //插入默认超级管理员
    void insertAdministration();
    //创建角色表
    void createRoleTable();
    //创建用户与角色的中间表
    void createUserRoleTable();
    //初始化默认权限
    void insertRole();
    //给超级管理员用户权限
    void insertAdminRole();
}
