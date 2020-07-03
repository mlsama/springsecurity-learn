package com.mlsama.springsecurity.utils;

/**
 * DESC: 加解密
 * AUTHOR:mlsama
 * 2020/7/3 15:11
 */
public class DecryptUtil {
    /**
     * 使用默认密钥进行DES加密
     */
    public static String encrypt(String plainText) {
        try {
            return new Des().encrypt(plainText);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 使用指定密钥进行DES解密
     */
    public static String encrypt(String plainText, String key) {
        try {
            return new Des(key).encrypt(plainText);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 使用默认密钥进行DES解密
     */
    public static String decrypt(String plainText) {
        try {
            return new Des().decrypt(plainText);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 使用指定密钥进行DES解密
     */
    public static String decrypt(String plainText, String key) {
        try {
            return new Des(key).decrypt(plainText);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String str = "123456";
        String t = DecryptUtil.encrypt(str);
        System.out.println("原字符串：" + str);
        System.out.println("加密后：" + t);
        System.out.println("解密后：" + DecryptUtil.decrypt(t));
    }
}
