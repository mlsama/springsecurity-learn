package com.mlsama.springsecurity.handle;

import com.mlsama.springsecurity.entity.BaseResponse;
import com.mlsama.springsecurity.entity.SysUser;
import com.mlsama.springsecurity.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * DESC: 登陆失败处理器
 * AUTHOR:mlsama
 * 2020/6/28 10:23
 */
@Component
@Slf4j
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        Integer code;
        String msg;
        if (e instanceof UsernameNotFoundException){ // 用户不存在
            code = 406;
            msg = "用户不存在";
        }else if (e instanceof BadCredentialsException) { // 密码错误
            code = 501;
            msg = "密码错误";
        } else if (e instanceof LockedException) { // 账户被锁
            code = 502;
            msg = "账户被锁";
        } else if (e instanceof CredentialsExpiredException) { // 密码过期
            code = 503;
            msg = "密码过期";
        } else if (e instanceof AccountExpiredException) { // 账户过期
            code = 504;
            msg = "账户过期";
        } else if (e instanceof DisabledException) { // 账户不可用
            code = 505;
            msg = "账户不可用";
        } else { // 登陆失败
            code = 506;
            msg = "登陆失败";
        }
        BaseResponse resp = new BaseResponse();
        resp.setCode(code);
        resp.setMsg(msg);
        String username = httpServletRequest.getParameter("username");
        log.info("用户[{}]于[{}]登录失败,失败原因：[{}]", username, new Date(), msg);
        ResponseUtil.INSTANT.write(httpServletResponse,resp);
    }
}
