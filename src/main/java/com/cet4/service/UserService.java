package com.cet4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cet4.pojo.po.UserPo;
import com.cet4.pojo.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface UserService extends IService<UserPo> {

    List<UserVo> findAll();

    UserVo login(String username, String password);
}
