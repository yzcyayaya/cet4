package com.cet4.controller;

import com.cet4.pojo.vo.ResponseBean;
import com.cet4.pojo.vo.StatusCode;
import com.cet4.service.impl.PersonalInfoServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@RestController
@RequestMapping("info")
public class PersonalInfoController {

    @Autowired
    private PersonalInfoServiceImpl personalInfoService;

    /**
     * 文件上传
     * 从excel获取个人信息
     */
    @PostMapping("personalInfo")
    public ResponseBean upload(MultipartFile file) {
        int total;
        try {
            total = personalInfoService.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(StatusCode.ErrorDatabase, e, "excel解析错误!");
        }
        return new ResponseBean(StatusCode.SUCCESS, total, "excel解析新增成功!");
    }
    @GetMapping("personalInfo")
    public ResponseBean getPersonalInfoById(){
        return null;
    }
}

