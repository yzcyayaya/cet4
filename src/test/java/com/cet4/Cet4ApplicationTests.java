package com.cet4;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.cet4.dao.PersonalInfoDao;
import com.cet4.pojo.bo.PersonalInfoBo;
import com.cet4.pojo.po.PersonalInfoPo;
import com.cet4.service.PersonalInfoService;
import com.cet4.utils.ExcelPersonalInfoListener;
import com.cet4.utils.MD5Util;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j

@Ignore
class Cet4ApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private PersonalInfoDao personalInfoDao;

    @Autowired
    private PersonalInfoService personalInfoService;

    @Test
    public void simpleRead() {
    }
    @Test
    public void encode(){
        MD5Util md5Util = new MD5Util();
        String encode = md5Util.encode("123456");

        String password = "ylzlcl@163.com".substring(0,4) + "17687921824".substring(7,11);
        System.out.println(password);
        System.out.println(new MD5Util().encode(password));
    }

    @Test
    public void random(){
        String s = RandomUtil.randomNumbers(4);
        System.out.println(s);
    }
}
