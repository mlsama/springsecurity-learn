package com.mlsama.springsecurity.service.impl;

import com.mlsama.springsecurity.entity.SysUser;
import com.mlsama.springsecurity.mapper.UserMapper;
import com.mlsama.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * DESC: 认证
 * AUTHOR:mlsama
 * 2020/6/28 9:55
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    /**
     * 登陆
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userMapper.selectUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException(username + "不存在");
        }
        log.info("登陆的用户信息:{}",sysUser);
        return sysUser;
    }


    @Override
    public SysUser getUser(Long id) {
        SysUser user = new SysUser();
        user.setId(1L);
        SysUser result = userMapper.selectOne(user);
        log.info("user={}",result);
        return result;
    }
}
