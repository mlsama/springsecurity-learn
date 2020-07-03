package com.mlsama.springsecurity.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mlsama.springsecurity.SpringSecurityApplication;
import com.mlsama.springsecurity.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringSecurityApplication.class)
public class UserServiceImplTest {
    @Resource
    private UserService userService;
    @Test
    public void loadUserByUsername() throws JsonProcessingException {
        UserDetails userDetails = userService.loadUserByUsername("admin");
        System.out.println(new ObjectMapper().writeValueAsString(userDetails));
    }
}