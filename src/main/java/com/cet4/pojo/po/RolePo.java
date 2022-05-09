package com.cet4.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePo {
    @TableField("ROSE_ID")
    private int id;
    @TableField("ROSE_NAME")
    private String name;
}
