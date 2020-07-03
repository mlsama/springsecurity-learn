package com.mlsama.springsecurity.service;

import com.mlsama.springsecurity.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * DESC: 用户接口
 * AUTHOR:mlsama
 * 2020/6/28 9:52
 */
public interface UserService extends UserDetailsService {

    SysUser getUser(Long id);

}
