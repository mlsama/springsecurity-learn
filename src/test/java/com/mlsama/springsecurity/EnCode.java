package com.mlsama.springsecurity;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * DESC:
 * AUTHOR:mlsama
 * 2020/6/28 16:51
 */
public class EnCode {

    @Test
    public void encode(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String s = encoder.encode("123456");
        System.out.println(s);
        System.out.println(s.length());
    }
}
