package com.junyang.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * 类: MD5Util <br>
 * 描述: md5加密工具<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年11月05日 19:07
 */
public class MD5Util {
    private final static Logger logger = LoggerFactory.getLogger(MD5Util.class);
    private final static char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    public static String MD5(String s) {
        logger.info("md5加密字符串:{}",s);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {

        StringBuilder ret = new StringBuilder(hexDigits.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(hexDigits[(bytes[i] >> 4) & 0x0f]);
            ret.append(hexDigits[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }


    public static void main(String[] args) {
        String context = "123456wewewewewewew";
        System.out.println(MD5Util.MD5(context));
    }
}
