package com.cet4.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cet4.dao.PersonalInfoDao;
import com.cet4.dao.UserDao;
import com.cet4.pojo.bo.PersonalInfoBo;
import com.cet4.pojo.po.PersonalInfoPo;
import com.cet4.pojo.po.UserPo;
import com.cet4.service.PersonalInfoService;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class PersonalInfoServiceImpl extends ServiceImpl<PersonalInfoDao, PersonalInfoPo> implements PersonalInfoService {
    @Autowired
    private PersonalInfoDao personalInfoDao;

    @Autowired
    private UserDao userDao;

    public int save(MultipartFile file) throws IOException {
        //UserPo对象集合
        List<UserPo> userPoList = new ArrayList<>();
        //PersonalInfoPo对象集合
        List<PersonalInfoPo> personalList = new ArrayList<>();
        //从excel中解析
        EasyExcel.read(file.getInputStream(), PersonalInfoBo.class,new PageReadListener<PersonalInfoBo>(dataList -> {
            System.out.println(dataList.size());
            for (PersonalInfoBo personalInfoBo : dataList) {
                log.info("读取到一条数据{}", JSONUtil.toJsonStr(personalInfoBo));
                //先添加用户基础信息
                UserPo userPo = new UserPo();
                userPo.setId(UUID.fastUUID().toString());
                //设置好手机号码
                userPo.setPhone(personalInfoBo.getPhone());
                userPo.setCreatedTime(new Date());
                userPoList.add(userPo);
                //再添加用户个人信息
                PersonalInfoPo personalInfoPo = new PersonalInfoPo(personalInfoBo);
                personalInfoPo.setCreatedTime(userPo.getCreatedTime());
                personalInfoPo.setUserId(userPo.getId());
                personalList.add(personalInfoPo);
            }
        })).sheet().doRead();
        //保存用户基础信息
        userDao.save(userPoList);
        //保存用户个人信息
        return personalInfoDao.save(personalList);
    }
}
