package com.junyang.security.vo;

import java.io.Serializable;

/**
 * 类: LoginVo <br>
 * 描述: 登录参数<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年11月01日 11:09
 */
public class LoginVo implements Serializable {

    private static final long serialVersionUID = -3682470632165815065L;
    private String loginName;
    private String pwd;
    private String randomCode;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
