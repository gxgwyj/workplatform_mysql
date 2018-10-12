package com.junyang.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.junyang.common.Constants;
import com.junyang.security.vo.PersonVo;

public class ControllerUtil {
	public static PersonVo  getCurrentUser(){
		Subject  currentUser = SecurityUtils.getSubject();//，获取当前的用户
		Session session = currentUser.getSession();
		PersonVo personVo = (PersonVo) session.getAttribute(Constants.SESSION_USER);//从session中获取用户
		return personVo;
	}

}
