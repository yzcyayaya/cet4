package com.cet4.config;

import com.cet4.pojo.bo.UserBo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserSecurity extends User {

    public String id;

    public UserSecurity(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    public UserSecurity(UserBo userBo, Collection<? extends GrantedAuthority> authorities) {
        this(userBo.getUsername(),userBo.getPassword(),authorities);
        this.id = userBo.getId();
    }
}
