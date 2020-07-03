package com.mlsama.springsecurity.config;

import com.mlsama.springsecurity.entity.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

/**
 * DESC: 异常统一处理
 * AUTHOR:mlsama
 * 2020/6/29 10:17
 */
// 处理controller包下的类
@ControllerAdvice(basePackages = "com.mlsama.springsecurity.controller")
public class ExceptionAdvice {

    /**
     * 异常处理器,可以指定处理的异常,可以指定多个
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse exceptionHandler(Exception e){
        int code = 0;
        String msg = null;
        BaseResponse resp = new BaseResponse();
        if (e instanceof AccessDeniedException){
            code = 403;
            msg = "权限不足";
        }
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }
}
