package com.junyang.security.vo;

import java.io.Serializable;

/**
 * 类: SecurityDataVo <br>
 * 描述: 加密安全数据<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年11月01日 11:19
 */
public class SecurityDataVo implements Serializable {

    private static final long serialVersionUID = 2919282548745257408L;
    private String data;
    private String sign;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
