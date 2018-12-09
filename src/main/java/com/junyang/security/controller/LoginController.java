package com.junyang.security.controller;

import com.junyang.common.Constants;
import com.junyang.common.model.ServiceResponse;
import com.junyang.security.service.LoginService;
import com.junyang.security.service.MenuService;
import com.junyang.security.vo.SecurityDataVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登陆处理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="security/")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private MenuService menuService;

	@Autowired
	private LoginService loginService;
	
	/**
	 * 登陆
	 */
	@RequestMapping(value="login")
	@ResponseBody
	private ServiceResponse login(@RequestBody SecurityDataVo securityDataVo){
		return loginService.loginValidate(securityDataVo);
	}
	/**
	 * 退出
	 */
	@RequestMapping(value="logout")
	private ModelAndView logout(){
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.removeAttribute(Constants.SESSION_USER);
		//shiro销毁登录
		subject.logout();
	   return new ModelAndView("redirect:/login.jsp");
	}

	@RequestMapping(value="home")
	@ResponseBody
	public ServiceResponse home() {
//		String userId = ControllerUtil.getCurrentUser().getId();
		String userId = "001";
		return loginService.homePage(userId);
	}

}
