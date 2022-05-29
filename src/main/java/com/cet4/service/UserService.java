package com.cet4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cet4.pojo.po.UserPo;
import com.cet4.pojo.vo.UserVo;


import java.util.List;

public interface UserService extends IService<UserPo> {

    List<UserVo> findAll(Integer flag,String vague);

    UserVo login(String username, String password);

    UserPo findUserByEmail(String email);
}
