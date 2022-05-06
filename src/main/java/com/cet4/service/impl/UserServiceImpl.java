package com.cet4.service.impl;

import com.cet4.dao.UserDao;
import com.cet4.pojo.po.UserPo;
import com.cet4.pojo.vo.UserVo;
import com.cet4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<UserVo> findAll(){
        List<UserPo> userPos = userDao.selectList(null);
        List<UserVo> userVos = new ArrayList<>();
        for (UserPo userPo : userPos) {
            userVos.add(new UserVo(userPo.getId(),userPo.getUsername(),userPo.getPhone(),userPo.getEmail()));
        }
        return userVos;
    }

    @Override
    public UserVo login(String username, String password) {
        UserPo userPo = userDao.login(username, password);
        return new UserVo(userPo.getId(),userPo.getUsername(),userPo.getPhone(),userPo.getEmail());
    }
}
