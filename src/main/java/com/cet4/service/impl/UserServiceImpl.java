package com.cet4.service.impl;

import com.cet4.dao.UserMapper;
import com.cet4.pojo.po.UserPo;
import com.cet4.pojo.vo.UserVo;
import com.cet4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserVo> findAll(){
        List<UserPo> userPos = userMapper.selectList(null);
        List<UserVo> userVos = new ArrayList<>();
        for (UserPo userPo : userPos) {
            userVos.add(new UserVo(userPo.getId(),userPo.getUsername(),userPo.getPhone(),userPo.getEmail()));
        }
        return userVos;
    }

    @Override
    public UserVo login(String username, String password) {
        UserPo userPo = userMapper.login(username, password);
        return new UserVo(userPo.getId(),userPo.getUsername(),userPo.getPhone(),userPo.getEmail());
    }
}
