package com.mlsama.springsecurity.handle;

import com.mlsama.springsecurity.utils.DecryptUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * DESC: 自定义密码加解密处理器
 * AUTHOR:mlsama
 * 2020/7/3 15:51
 */
@Component
public class PasswordEncoderHandle implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return DecryptUtil.encrypt(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodePass) {
        return DecryptUtil.encrypt(charSequence.toString()).equals(encodePass);
    }
}
