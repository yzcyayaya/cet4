package com.cet4.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cet4.dao.PersonalInfoDao;
import com.cet4.pojo.po.PersonalInfoPo;
import com.cet4.service.PersonalInfoService;
import org.springframework.stereotype.Service;

@Service
public class PersonalInfoServiceImpl extends ServiceImpl<PersonalInfoDao, PersonalInfoPo> implements PersonalInfoService {
}
