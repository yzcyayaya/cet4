package com.cet4.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cet4.dao.RoleDao;
import com.cet4.dao.UserDao;
import com.cet4.pojo.bo.UserBo;
import com.cet4.pojo.po.RolePo;
import com.cet4.pojo.po.UserPo;
import com.cet4.pojo.vo.StatusCode;
import com.cet4.service.UserService;
import com.cet4.utils.RegularUtil;
import com.cet4.utils.TimeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//登录相关
@Log4j2
@Service
public class CustomUserServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //登录用户信息
        UserPo userPo = null;
        TimeUtil timeUtil = new TimeUtil();
//        userService.update(); 忘记干嘛的
        String recentlyLanded = timeUtil.getFormatDateForSix();
        //username 可能是手机号码和邮箱
        if (RegularUtil.regularPhone(username)) {   //电话登录
            userPo = userDao.findUserByPhone(username);
            log.info(userPo.getUsername() + "在：" + recentlyLanded + "以手机登录的。");
            userPo.setUsername(userPo.getPhone()); //用户为手机号存储
            //更新最近登录时间
            log.info("登录时间更新" + recentlyLanded);
            userDao.updateRecentlyLandedByPhone(userPo.getUsername(), recentlyLanded);
        } else if (RegularUtil.regularEmail(username)) {  //邮箱登录
            userPo = userDao.findUserByEmail(username);
            log.info(userPo.getUsername() + "在：" + recentlyLanded + "以邮箱登录的。");
            userPo.setUsername(userPo.getPhone()); //用户为邮箱存储
            //更新最近登录时间
            log.info("登录时间更新" + recentlyLanded);
            userDao.updateRecentlyLandedByEmail(userPo.getUsername(), recentlyLanded);
        }
        if (userPo == null) {
            throw new UsernameNotFoundException(StatusCode.getMsg(StatusCode.ErrorNotExistUser));
        }

        //还需要查找该用户权限
        List<RolePo> roles = roleDao.findAllByUserId(userPo.getId());
        UserBo userBo = new UserBo(userPo, recentlyLanded, roles);
        log.info(userBo);
        //权限集合,因为单个用户可能有多个权限
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (RolePo rolePo : userBo.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(rolePo.getName()));
        }
        return new UserSecurity(userBo, authorities);
    }
}