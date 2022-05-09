package com.cet4.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cet4.pojo.bo.UserBo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPo {
    /**
     * id
     */
    @TableId(value = "USER_ID")
    private String id;
    /**
     * 乐观锁
     */
    @TableField(value = "REVISION")
    private String revision;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "CREATED_TIME")
    private Date createdTime;
    /**
     * 更新人
     */
    @TableField(value = "UPDATED_BY")
    private String updatedBy;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "UPDATED_TIME")
    private Date updatedTime;
    /**
     * 邮箱
     */
    @TableField(value = "EMAIL")
    private String email;

    /**
     * 手机
     */
    @TableField(value = "PHONE_NUMBER")
    private String phone;


    /**
     * 用户名
     */
    @TableField(value = "USER_NAME")
    private String username;
    /**
     * 密码 !!!必须脱敏
     */
    @TableField(value = "PASSWORD")
    private String password;

    /**
     * 最后登录时间
     */
    @TableField("RECENTLY_LANDED")
    private String recentlyLanded;

    public UserPo(UserBo userBo){
        this.id = userBo.getId();
        this.username = userBo.getUsername();
        this.password = userBo.getPassword();
        this.phone = userBo.getPhone();
        this.email = userBo.getEmail();
    }
}
