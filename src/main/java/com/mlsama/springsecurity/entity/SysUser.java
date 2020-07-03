package com.mlsama.springsecurity.entity;

import lombok.Data;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * DESC: security 用户
 * AUTHOR:mlsama
 * 2020/6/28 9:27
 */
@Data
@Table(name = "T_DAP_USER") // 指定表名
public class SysUser implements UserDetails {
    @Id
    // id增长策略
    // 方式一
    /*@GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "select SEQ_DAP_USER.nextval from dual" // id由序列生成
    )*/
    // 方式二,useGeneratedKeys = true: 返回主键
    @KeySql(sql = "select SEQ_DAP_USER.nextval from dual", order = ORDER.BEFORE)
    // 指定列名,大小写不敏感,在配置文件开启驼峰匹配即可匹配_
    @Column(name = "ID")
    private Long id;
    private String username;
    private String password;
    private String state;
    private List<GrantKey> grantKeys;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 用户权限集合,这里是资源授权码
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantKeys;
    }

    /**
     * 账号没过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号没被锁
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码没过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return state.equals("1");
    }
}
