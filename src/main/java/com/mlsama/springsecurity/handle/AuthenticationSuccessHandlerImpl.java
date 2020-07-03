package com.mlsama.springsecurity.handle;

import com.mlsama.springsecurity.entity.BaseResponse;
import com.mlsama.springsecurity.entity.SysUser;
import com.mlsama.springsecurity.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * DESC: 认证成功处理器
 * AUTHOR:mlsama
 * 2020/6/28 10:18
 */
@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        //登录成功后获取当前登录用户
        SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户[{}]于[{}]登录成功!", user.getUsername(), new Date());
        BaseResponse<SysUser> resp = new BaseResponse();
        resp.setCode(200);
        resp.setMsg("登录成功");
        resp.setData(user);
        ResponseUtil.INSTANT.write(httpServletResponse,resp);
    }
}
