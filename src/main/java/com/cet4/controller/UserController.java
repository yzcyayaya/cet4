package com.cet4.controller;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cet4.dao.RoleDao;
import com.cet4.pojo.bo.UserBo;
import com.cet4.pojo.po.UserPo;
import com.cet4.pojo.vo.ResponseBean;
import com.cet4.pojo.vo.StatusCode;
import com.cet4.pojo.vo.UserVo;
import com.cet4.service.impl.PersonalInfoServiceImpl;
import com.cet4.service.impl.UserServiceImpl;
import com.cet4.utils.MD5Util;
import com.cet4.utils.RegularUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

@Log4j2
@RestController
public class UserController {

    private String captcha;

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private PersonalInfoServiceImpl personalInfoService;
    @Autowired
    private RoleDao roleDao;
    @GetMapping("users")
    public ResponseBean findAll(Integer flag,String vague){
        return new ResponseBean(StatusCode.SUCCESS,userServiceImpl.findAll(flag,vague),"查询成功!");
    }
    @PostMapping("user/login")
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
    @PostMapping("user")
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

    @GetMapping("user/{id}")
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
    @PutMapping("user")
    public ResponseBean edit(UserBo userBo){
        try {
            //条件更新
            UpdateWrapper<UserPo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("USER_ID",userBo.getId());
            UserPo userPo = new UserPo(userBo);
            //加密
            userPo.setPassword(new MD5Util().encode(userPo.getPassword()));
            //更新时间
            userPo.setUpdatedTime(new Date());
            userServiceImpl.update(userPo,updateWrapper);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseBean(StatusCode.ErrorDatabase,null,StatusCode.getMsg(StatusCode.ErrorDatabase));
        }
        return new ResponseBean(StatusCode.SUCCESS,null,StatusCode.getMsg(StatusCode.SUCCESS));
    }
    @DeleteMapping("user")
    public ResponseBean delete(String ids){
        String[] split = ids.split(",");
        ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings,split);
        try{
            //必须先删除外键信息表
            personalInfoService.removeBatchByIds(strings);
            roleDao.deleteRoleByUserId(strings);
            //实际删除用户
            userServiceImpl.removeBatchByIds(strings);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseBean(StatusCode.ErrorDatabase,null,StatusCode.getMsg(StatusCode.ErrorDatabase));
        }
        return new ResponseBean(StatusCode.SUCCESS,null,"删除成功！");
    }

    @PostMapping("user/captcha")
    public ResponseBean responseBean(String email){
        //随机数验证码
        captcha = RandomUtil.randomNumbers(4);

        //正则
        if(!RegularUtil.regularEmail(email)){
            return new ResponseBean(StatusCode.ErrorAuth,null,"请输入正确的邮箱地址！");
        }
        //确认邮箱存在

        UserPo userPo = userServiceImpl.findUserByEmail(email);
        if(null == userPo){
            return new ResponseBean(StatusCode.ErrorNotExistUser,null,"该邮箱不存在，请重新输入！");
        }
        //发送邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("四六级系统(demo)重置密码请求");
            //内容
            helper.setText("<b style='color:hsl(200, 50%, 60%);'>"+"您的验证码为:"+captcha+"</b><p><b>请妥善保管,不要给他人使用，如不是您申请的，请忽略.</b><p><img src='https://mi-2.oss-cn-hangzhou.aliyuncs.com/public/warmth/"+ new Random().nextInt(42) +".jpg'></img>",true);
            helper.setTo(userPo.getEmail());
            helper.setFrom("ylzlcl@163.com");
            //发送
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ResponseBean(StatusCode.ErrorCaptcha,null,"发送验证码错误！");
        }
        return new ResponseBean(StatusCode.SUCCESS,null,"发送验证码成功！");
    }

    @PostMapping("user/restPassword")
    public ResponseBean restPassword(String email,String password,String confirmPassword,int code){
        //再次确认邮箱存在
        UserPo userPo = userServiceImpl.findUserByEmail(email);
        if(null == userPo){
            return new ResponseBean(StatusCode.ErrorNotExistUser,null,"该邮箱不存在，请重新输入！");
        }
        try{
            //匹配二次密码
            if (!password.equals(confirmPassword)){
                return new ResponseBean(StatusCode.ErrorNotCompare,null,"俩次密码不匹配，请重新输入！");
            }
            //匹配验证码
            if (Integer.parseInt(captcha) != code){
                return new ResponseBean(StatusCode.ErrorCaptcha,null,StatusCode.getMsg(StatusCode.ErrorCaptcha));
            }
            //更新密码
            userPo.setPassword(new MD5Util().encode(password));
            userServiceImpl.updateById(userPo);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseBean(StatusCode.ErrorDatabase,null,StatusCode.getMsg(StatusCode.ErrorDatabase));
        }
        //使验证码失效
        captcha = "";
        return new ResponseBean(StatusCode.SUCCESS,null,"密码重置成功，去登录吧！");
    }
}
