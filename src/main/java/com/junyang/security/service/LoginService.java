package com.junyang.security.service;


import com.junyang.common.model.ApiResponse;
import com.junyang.security.vo.SecurityDataVo;

public interface LoginService {
	ApiResponse loginValidate(SecurityDataVo securityDataVo);
}
