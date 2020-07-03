package com.mlsama.springsecurity.entity;

import lombok.Data;

/**
 * DESC:
 * AUTHOR:mlsama
 * 2020/6/28 10:14
 */
@Data
public class BaseResponse<T> {
    private int code;
    private String msg;
    private T data;
}
