package com.junyang.security.service.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.junyang.common.Constants;
import com.junyang.common.MessageEnum;
import com.junyang.common.model.ApiResponse;
import com.junyang.common.utils.JsonUtil;
import com.junyang.common.utils.MD5Util;
import com.junyang.common.utils.RSAUtil;
import com.junyang.security.controller.RandomCodeController;
import com.junyang.security.service.LoginService;
import com.junyang.security.vo.LoginVo;
import com.junyang.security.vo.SecurityDataVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Override
	public ApiResponse loginValidate(SecurityDataVo securityDataVo){
		logger.info("获取到的请求数据:{}",JsonUtil.Object2Json(securityDataVo));
		ApiResponse response = new ApiResponse();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String sessionRandomCode = (String) session.getAttribute(RandomCodeController.SESSION_VALID_CODE);
		try {
			String sign = securityDataVo.getSign();
			String data = RSAUtil.privateDecrypt(securityDataVo.getData(), Constants.PRIVATE_KEY);
			logger.info("解密后的登录数据：{}", data);
			LoginVo loginVo = JSON.parse(data, LoginVo.class);
			checkRandomCode(sessionRandomCode, loginVo);
			checkSign(sign, loginVo);
			String md5Pwd = MD5Util.MD5(String.format(Constants.LOGIN_PWD_KEY, loginVo.getPwd()));
			logger.info("Md5-PWD:{}",md5Pwd);
			UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getLoginName(), md5Pwd);
			subject.login(token);
			session.removeAttribute(RandomCodeController.SESSION_VALID_CODE);
			response.setRes_code(MessageEnum.SUCCESS.getCode());
			response.setRes_msg(MessageEnum.SUCCESS.getText());

		} catch (AuthenticationException e) {
			logger.error("登录异常", e);
			response.setRes_code(MessageEnum.FAIL.getCode());
			response.setRes_msg("登录验证失败");
		} catch (Exception e) {
			logger.error("登录异常", e);
			response.setRes_code(MessageEnum.FAIL.getCode());
			response.setRes_msg(e.getMessage());
		}
		return response;
	}

	private void checkSign(String sign,LoginVo loginVo){
		String realSign = MD5Util.MD5(loginVo.getLoginName() + loginVo.getPwd() + loginVo.getRandomCode());
		if (!sign.equals(realSign)) {
			throw new RuntimeException("签名错误");
		}
	}
	private void checkRandomCode(String randomCode,LoginVo loginVo){
		if (!loginVo.getRandomCode().equals(randomCode)) {
			throw new RuntimeException("验证码错误");
		}
	}

}
