package com.junyang.security.service;


import com.junyang.common.model.ServiceResponse;
import com.junyang.security.vo.SecurityDataVo;

public interface LoginService {
    /**
     * 登录
     *
     * @param securityDataVo
     * @return
     */
    ServiceResponse loginValidate(SecurityDataVo securityDataVo);

    /**
     * 首页数据
     *
     * @param userId
     * @return
     */
    ServiceResponse homePage(String userId);
}
