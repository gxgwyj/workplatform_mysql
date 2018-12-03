package com.junyang.common.utils;

import com.junyang.common.Constants;
import com.junyang.security.vo.PersonVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ControllerUtil {
	public static PersonVo  getCurrentUser(){
		Subject  currentUser = SecurityUtils.getSubject();//，获取当前的用户
		Session session = currentUser.getSession();
		PersonVo personVo = (PersonVo) session.getAttribute(Constants.SESSION_USER);//从session中获取用户
		return personVo;
	}

}
