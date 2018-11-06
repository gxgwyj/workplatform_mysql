package com.junyang.common.exception;

/**
 * 类: RSADecryptException <br>
 * 描述: RSA解密异常<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年11月05日 18:39
 */
public class RSADecryptException extends Exception {
    public RSADecryptException(Exception e) {
        super("RSA解密异常",e);
    }
}
