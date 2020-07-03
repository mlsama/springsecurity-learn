package com.mlsama.springsecurity.handle;

import com.mlsama.springsecurity.entity.BaseResponse;
import com.mlsama.springsecurity.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DESC: 退出
 * AUTHOR:mlsama
 * 2020/6/28 10:37
 */
@Component
@Slf4j
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException, ServletException {
        BaseResponse resp = new BaseResponse();
        resp.setCode(200);
        resp.setMsg("退出成功");
        ResponseUtil.INSTANT.write(httpServletResponse,resp);
    }
}
