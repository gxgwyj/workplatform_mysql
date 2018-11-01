package com.junyang.security.service;


import com.junyang.security.vo.LoginMsg;

public interface LoginService {
	public LoginMsg loginValidate(String code, String password);
}
