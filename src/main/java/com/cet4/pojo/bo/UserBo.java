package com.cet4.pojo.bo;

import com.cet4.pojo.po.RolePo;
import com.cet4.pojo.po.UserPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBo {
    private String id;

    private String email;
    private String phone;
    private String username;
    private String password;

    private String recentlyLanded;
    private List<RolePo> roles;

    //该方法用于登录业务
    public UserBo(UserPo userPo,String recentlyLanded,List<RolePo> roles){
        this.id = userPo.getId();
        this.username = userPo.getUsername();
        this.password = userPo.getPassword();
        this.recentlyLanded = recentlyLanded;
        this.roles = roles;
    }
}
