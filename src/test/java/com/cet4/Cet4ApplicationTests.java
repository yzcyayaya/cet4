package com.cet4;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.cet4.dao.PersonalInfoDao;
import com.cet4.pojo.bo.PersonalInfoBo;
import com.cet4.pojo.po.PersonalInfoPo;
import com.cet4.service.PersonalInfoService;
import com.cet4.utils.ExcelPersonalInfoListener;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
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
        String fileName = "/home/m2/Documents/class_task/javaee_task/excel/test.xls";
        List<PersonalInfoPo> list = new ArrayList<>();
        EasyExcel.read(fileName, PersonalInfoBo.class,new PageReadListener<PersonalInfoBo>(dataList -> {
            System.out.println(dataList.size());
            for (PersonalInfoBo personalInfoBo : dataList) {
                log.info("读取到一条数据{}", JSONUtil.toJsonStr(personalInfoBo));
                PersonalInfoPo personalInfoPo = new PersonalInfoPo(personalInfoBo);
                personalInfoPo.setUserId(UUID.fastUUID().toString());
                list.add(personalInfoPo);
            }
        })).sheet().doRead();
//        EasyExcel.read(fileName, PersonalInfoBo.class,new ExcelPersonalInfoListener()).sheet().doRead();
//        personalInfoDao.save(list);
        personalInfoService.saveBatch(list);
    }
}
