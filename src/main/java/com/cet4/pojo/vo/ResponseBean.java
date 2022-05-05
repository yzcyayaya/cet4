package com.cet4.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class ResponseBean implements Serializable {
    private int code;
    private Object data;
    private String msg;
}
