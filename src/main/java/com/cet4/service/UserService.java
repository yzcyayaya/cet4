package com.cet4.service;

import com.cet4.pojo.po.UserPo;
import com.cet4.pojo.vo.UserVo;

import java.util.List;

public interface UserService {

    List<UserVo> findAll();

    UserVo login(String username, String password);
}
