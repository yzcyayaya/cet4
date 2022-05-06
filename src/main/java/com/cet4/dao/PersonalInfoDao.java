package com.cet4.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cet4.pojo.po.PersonalInfoPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonalInfoDao extends BaseMapper<PersonalInfoPo> {
    int save(@Param(value = "list") List<PersonalInfoPo> list);
}
