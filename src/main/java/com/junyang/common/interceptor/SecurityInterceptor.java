package com.junyang.common.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(true);  
        // 从session 里面获取用户名的信息  
        Object obj = session.getAttribute("user");  
        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行重新登陆提示  
        response.setContentType("text/html; charset=UTF-8");
        if (obj == null || "".equals(obj.toString())) {
        	PrintWriter out = response.getWriter();
        	StringBuffer  str = new StringBuffer();
        	str.append("<script>");
        	str.append("alert(\"操作超时，请重新登录\");");
        	str.append("window.top.location.href=").append("\""+request.getContextPath()+"\"");
        	str.append("</script>");
        	out.print(str.toString());
        	out.close();
        	return false;
        }  
        return true; 
	}
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}
