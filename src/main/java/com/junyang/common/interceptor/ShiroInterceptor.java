package com.junyang.common.interceptor;

import com.junyang.common.Constants;
import com.junyang.security.vo.PersonVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ShiroInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();//获取请求路径
		String per = request.getParameter("per");
		boolean  isOk = true; 
		if(!path.matches(Constants.NO_INTERCEPTOR_PATH)){//是否为过滤链接
			//shiro管理的session
			Subject  currentUser = SecurityUtils.getSubject();//，获取当前的用户
			Session session = currentUser.getSession();
			PersonVo personVo = (PersonVo) session.getAttribute(Constants.SESSION_USER);//从session中获取用户
			if(personVo==null){//登录超时
				response.setContentType("text/html; charset=UTF-8");
	        	PrintWriter out = response.getWriter();
	        	StringBuffer  str = new StringBuffer();
	        	str.append("<script>");
	        	str.append("alert(\"操作超时，请重新登录\");");
	        	str.append("window.top.location.href=").append("\""+request.getContextPath()+"\"");
	        	str.append("</script>");
	        	out.print(str.toString());
	        	out.close();
	        	isOk =  false;
	        }else{//登录未超时判断是否有权限
	        	if(per!=null &&"menu".equals(per)){
	        		Boolean isPermitted = currentUser.isPermitted(path);
		        	if(!isPermitted){//没有权限
		        		response.setContentType("text/html; charset=UTF-8");
			        	PrintWriter out = response.getWriter();
			        	StringBuffer  str = new StringBuffer();
			        	str.append("<script>");
			        	str.append("alert(\"您没有权限访问！\");");
			        	str.append("</script>");
			        	out.print(str.toString());
		        	}
		        	isOk =  isPermitted;
				}
	        }  
		}
		return isOk;
	}
}
