package com.mlsama.springsecurity.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * DESC: 资源授权码
 * AUTHOR:mlsama
 * 2020/6/28 9:38
 */
@Data
public class GrantKey implements GrantedAuthority {

    private String grantKey;
    @Override
    public String getAuthority() {
        return grantKey;
    }
}
