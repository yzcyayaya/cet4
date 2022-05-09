package com.cet4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cet4.dao.UserDao;
import com.cet4.pojo.po.UserPo;
import com.cet4.pojo.vo.UserVo;
import com.cet4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao,UserPo> implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<UserVo> findAll(){
        List<UserPo> userPos = userDao.selectList(null);
        List<UserVo> userVos = new ArrayList<>();
        for (UserPo userPo : userPos) {
            userVos.add(new UserVo(userPo));
        }
        return userVos;
    }

    @Override
    public UserVo login(String username, String password) {
        String phoneReg = "1[3456789]\\d{9}";
        String emailReg = "\\w{1,}@(\\w+\\.)+\\w+";
        UserPo userPo = null;
        if (username.matches(phoneReg)) {   //电话登录
            userPo = userDao.login(username, password,"phone");
        } else if (username.matches(emailReg)) {  //邮箱登录
            userPo = userDao.login(username, password,"email");
        }
        return new UserVo(userPo);
    }
}
