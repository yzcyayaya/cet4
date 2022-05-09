package com.cet4.controller;


import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cet4.pojo.bo.UserBo;
import com.cet4.pojo.po.UserPo;
import com.cet4.pojo.vo.ResponseBean;
import com.cet4.pojo.vo.StatusCode;
import com.cet4.pojo.vo.UserVo;
import com.cet4.service.impl.UserServiceImpl;
import com.cet4.utils.MD5Util;
import com.cet4.utils.RegularUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@Log4j2
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    public ResponseBean findAll(){
        return new ResponseBean(StatusCode.SUCCESS,userServiceImpl.findAll(),"查询成功!");
    }
    @PostMapping("/user/login")
    public ResponseBean login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        UserVo userVo = null;
        String md5Password = new MD5Util().encode(password);
        try{
            userVo = userServiceImpl.login(username, md5Password);
            if (null != userVo && null != userVo.getId() ){
                //登录标识位
                request.getSession().setAttribute("login","Yes");
                log.info( "用户名:" + username + "登录!");
                return new ResponseBean(StatusCode.SUCCESS, userVo,"登录成功!");
            } else {
                return new ResponseBean(StatusCode.ErrorNotExistUser,null, StatusCode.getMsg(StatusCode.ErrorNotExistUser));
            }
        }catch (Exception e){
            e.printStackTrace();
            log.warn(e.getMessage());
            return new ResponseBean(StatusCode.ErrorDatabase,null,StatusCode.getMsg(StatusCode.ErrorDatabase));
        }
    }
    @PostMapping("/user")
    public ResponseBean addUser(UserBo userBo){
        System.out.println(userBo+"test");
        //验证数据合法性
        if(!RegularUtil.regularPhone(userBo.getPhone())){
            return new ResponseBean(StatusCode.ErrorDataIllegal,null,"手机号"+StatusCode.getMsg(StatusCode.ErrorDataIllegal));
        }
        if (!RegularUtil.regularEmail(userBo.getEmail())){
            return new ResponseBean(StatusCode.ErrorDataIllegal,null,"邮箱"+StatusCode.getMsg(StatusCode.ErrorDataIllegal));
        }
        //uid
        userBo.setId(String.valueOf(UUID.fastUUID()));
        //加密密码
        userBo.setPassword(new MD5Util().encode(userBo.getPassword()));
        UserPo userPo = new UserPo(userBo);
        userPo.setCreatedTime(new Date());
        try{
            userServiceImpl.save(userPo);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseBean(StatusCode.ErrorDatabase,null,"新增失败!");
        }
        return new ResponseBean(StatusCode.SUCCESS,null,"新增成功");
    }

    @GetMapping("/user/{id}")
    public ResponseBean findUserById(@PathVariable("id")String id){
        UserPo userPo = null;
        try{
            userPo = userServiceImpl.getById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseBean(StatusCode.ErrorDatabase,null,StatusCode.getMsg(StatusCode.ErrorDatabase));
        }finally {
            if (null == userPo){
                return new ResponseBean(StatusCode.ErrorNotExistUser,null,StatusCode.getMsg(StatusCode.ErrorNotExistUser));
            }
        }
        return new ResponseBean(StatusCode.SUCCESS,new UserVo(userPo),StatusCode.getMsg(StatusCode.SUCCESS));
    }
    @PutMapping("/user")
    public ResponseBean edit(UserBo userBo){
        try {
            UpdateWrapper<UserPo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("USER_ID",userBo.getId());
            UserPo userPo = new UserPo(userBo);
            //更新时间
            userPo.setUpdatedTime(new Date());
            userServiceImpl.update(userPo,updateWrapper);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseBean(StatusCode.ErrorDatabase,null,StatusCode.getMsg(StatusCode.ErrorDatabase));
        }
        return new ResponseBean(StatusCode.SUCCESS,null,StatusCode.getMsg(StatusCode.SUCCESS));
    }
    @DeleteMapping("/user")
    public ResponseBean delete(String ids){
        String[] split = ids.split(",");
        ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings,split);
        try{
            userServiceImpl.removeBatchByIds(strings);
        }catch (Exception e){
            return new ResponseBean(StatusCode.ErrorDatabase,null,StatusCode.getMsg(StatusCode.ErrorDatabase));
        }
        return new ResponseBean(StatusCode.SUCCESS,null,StatusCode.getMsg(StatusCode.SUCCESS));
    }
}
