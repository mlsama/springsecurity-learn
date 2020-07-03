package com.mlsama.springsecurity.handle;

import com.mlsama.springsecurity.entity.BaseResponse;
import com.mlsama.springsecurity.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DESC: 权限不足处理器
 * AUTHOR:mlsama
 * 2020/6/28 10:41
 */
@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        BaseResponse resp = new BaseResponse();
        resp.setCode(403);
        resp.setMsg("权限不足");
        ResponseUtil.INSTANT.write(httpServletResponse,resp);
    }
}
