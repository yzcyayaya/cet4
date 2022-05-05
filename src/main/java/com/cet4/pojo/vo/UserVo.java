package com.cet4.pojo.vo;

import com.cet4.pojo.po.UserPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {
    private String id;
    private String username;
    private int phone;
    private String email;
}
