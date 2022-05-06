package com.cet4.controller;


import com.cet4.pojo.vo.ResponseBean;
import com.cet4.pojo.vo.StatusCode;
import com.cet4.pojo.vo.UserVo;
import com.cet4.service.impl.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("all")
    public ResponseBean findAll(){
        return new ResponseBean(200,userServiceImpl.findAll(),"查询成功!");
    }
    @PostMapping("login")
    public ResponseBean login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        UserVo userPo = null;
        try{
            userPo = userServiceImpl.login(username, password);
            if (null != userPo){
                //登录标识位
                request.getSession().setAttribute("login","Yes");
                log.info( "用户名:" + username + "登录!");
                return new ResponseBean(StatusCode.SUCCESS, userPo,"登录成功!");
            } else {
                return new ResponseBean(StatusCode.ErrorNotExistUser,null, StatusCode.getMsg(StatusCode.ErrorNotExistUser));
            }
        }catch (Exception e){
            log.warn(e.getMessage());
            return new ResponseBean(StatusCode.ErrorDatabase,null,"登录失败，请重新尝试!");
        }
    }
}
