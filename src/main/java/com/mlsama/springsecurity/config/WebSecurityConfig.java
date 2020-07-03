package com.mlsama.springsecurity.config;

import com.mlsama.springsecurity.handle.*;
import com.mlsama.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.annotation.Resource;

/**
 * DESC: spring security核心配置
 * AUTHOR:mlsama
 * 2020/6/28 10:03
 */
@Configuration
// 开启spring security
@EnableWebSecurity
// 开启全局方法权限
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;
    @Resource
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;
    @Resource
    private LogoutSuccessHandlerImpl logoutSuccessHandler;
    @Resource
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;
    @Resource
    private CorsConfigurationSource corsConfigurationSource;
    @Resource
    private PasswordEncoderHandle passwordEncoderHandle;

    /**
     * 授权
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                // 设置放行请求,可以是多个
                .antMatchers("/api/user/userMenus","/api/user/sessionTimeout").permitAll()
                // 放行预请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                //其余请求全部需要登录后访问
                .anyRequest().authenticated()
                //前台提交就必须要用form表单提交，数据格式为表单格式,请求方法为 post,这个处理器是spring security内置的
                // /api/user/login可任意,写成前端请求路径即可
                .and().formLogin().loginPage("/login.html").loginProcessingUrl("/api/user/login").permitAll()
                //登录成功后的返回结果
                .successHandler(authenticationSuccessHandler)
                //登录失败后的返回结果
                .failureHandler(authenticationFailureHandler)
                //这里配置的logoutUrl为登出接口，并设置可匿名访问
                .and().logout().logoutUrl("/api/user/logout").permitAll()
                //登出后的返回结果
                .logoutSuccessHandler(logoutSuccessHandler)
                // 清空session
                .invalidateHttpSession(true)
                //这里配置的为当未登录访问受保护资源时，返回权限不足
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPointHandler)
                // session 管理
                .and().sessionManagement()
                // 设置Session失效跳转页面
                .invalidSessionUrl("/api/user/sessionTimeout")
                // 一个账号只能在一个地方登陆
                .maximumSessions(1).and()
                // 允许跨域访问
                .and().cors().configurationSource(corsConfigurationSource)
                // 不设置跨站伪造请求,这个要写在最后
                .and().csrf().disable();

    }

    /**
     * 认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoderHandle);
    }

    /**
     * 使用spring security内置的加密方法加密密码
     * @return
     */
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        //配置密码加密，这里声明成bean，方便注册用户时直接注入
        return passwordEncoderHandle;
    }*/
}
