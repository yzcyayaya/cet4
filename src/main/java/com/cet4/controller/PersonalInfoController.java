package com.cet4.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cet4.dao.RoleDao;
import com.cet4.pojo.bo.DemoExcelData;
import com.cet4.pojo.bo.PersonalInfoBo;
import com.cet4.pojo.po.PersonalInfoPo;
import com.cet4.pojo.vo.PersonalInfoVo;
import com.cet4.pojo.vo.ResponseBean;
import com.cet4.pojo.vo.StatusCode;
import com.cet4.service.impl.PersonalInfoServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @GetMapping("personalInfo/{id}")
    public ResponseBean getPersonalInfoById(@PathVariable("id")String id){
        PersonalInfoPo personalInfoPo = null;
        PersonalInfoVo personalInfoVo = null;
        try{
            //条件查询
            QueryWrapper<PersonalInfoPo> personalInfoPoQueryWrapper = new QueryWrapper<>();
            personalInfoPoQueryWrapper.eq("USER_ID",id);
            personalInfoPo = personalInfoService.getOne(personalInfoPoQueryWrapper);
            personalInfoVo = new PersonalInfoVo(personalInfoPo);
        }catch (Exception e){
            e.printStackTrace();
            if (null == personalInfoPo){
                return  new ResponseBean(StatusCode.ErrorAuth,null,"系统人员不允许查看详情！");
            }
        }
        return new ResponseBean(StatusCode.SUCCESS,personalInfoVo,"查询详情成功！");
    }

    @GetMapping("getDemoExcel")
    public void downloadDemo(HttpServletResponse response){

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = null;
        try {
            fileName = URLEncoder.encode("模板文件", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), PersonalInfoBo.class).sheet("模板").doWrite(new ArrayList<PersonalInfoBo>());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

