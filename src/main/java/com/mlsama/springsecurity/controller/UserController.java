package com.mlsama.springsecurity.controller;

import com.mlsama.springsecurity.entity.BaseResponse;
import com.mlsama.springsecurity.entity.SysUser;
import com.mlsama.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.annotation.Resource;


/**
 * DESC: 用户控制器
 * AUTHOR:mlsama
 * 2020/6/28 14:57
 */
@Controller
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;
    /**
     * 设置权限,方法要与@EnableGlobalMethodSecurity(prePostEnabled = true)开启的注解支持一致
     * prePostEnabled-->@PreAuthorize("hasAuthority('/api/user/userMenus')")
     *      hasAuthority: 权限授权：用户自定义的授权码，返回的UserDetails的Authority只要与这里匹配就可以，这里不需要前缀ROLE_，名称保持一至即可
     *      hasRole: 角色授权：角色名称，在我们返回的UserDetails的Authority需要加ROLE_前缀，Controller上使用时不要加前缀
     * securedEnabled-->@Secured("ROLE_xxx")
     * jsr250Enabled-->@RolesAllowed("ROLE_xxx")
     * 后2种注意角色是带ROLE_前缀的
     */
    //DelegatingFilterProxy
    //HttpSessionSecurityContextRepository
    //DefaultSavedRequest
    @GetMapping("/list")
    // hasAuthority根据授权码授权
    @PreAuthorize("hasAuthority('/api/user/list')")
    @ResponseBody
    public BaseResponse list(){
        BaseResponse resp = new BaseResponse();
        SysUser user = userService.getUser(1L);
        resp.setCode(200);
        resp.setMsg("成功");
        resp.setData(user);
        return resp;
    }

    @GetMapping("/sessionTimeout")
    @ResponseBody
    public BaseResponse sessionTimeout(){
        BaseResponse resp = new BaseResponse();
        resp.setCode(167);
        resp.setMsg("登陆已过期,请重新登陆");
        return resp;
    }
}
