package com.junyang.common.filter;

import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyCharacterEncodingFilter extends CharacterEncodingFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		super.doFilterInternal(request, response, filterChain);
	}

	@Override
	public void setEncoding(String encoding) {
		// TODO Auto-generated method stub
		super.setEncoding(encoding);
	}

	@Override
	public void setForceEncoding(boolean forceEncoding) {
		// TODO Auto-generated method stub
		super.setForceEncoding(forceEncoding);
	}

}
