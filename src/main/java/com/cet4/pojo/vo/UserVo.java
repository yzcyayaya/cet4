package com.cet4.pojo.vo;

import com.cet4.pojo.po.RolePo;
import com.cet4.pojo.po.UserPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {
    private String id;
    private String username;
    private String phone;
    private String email;

    private String recentlyLanded;

    public UserVo(UserPo userPo){
        this.id = userPo.getId();
        this.username = userPo.getUsername();

        this.phone = userPo.getPhone();
        this.email = userPo.getEmail();
        this.recentlyLanded = userPo.getRecentlyLanded();
    }
}
