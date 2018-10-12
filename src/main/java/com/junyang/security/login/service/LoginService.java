package com.junyang.security.login.service;

import javax.jws.WebService;

import com.junyang.security.login.model.LoginMsg;
@WebService
public interface LoginService {
	public LoginMsg loginValidate(String code,String password);
}
