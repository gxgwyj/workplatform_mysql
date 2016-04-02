package com.junyang.ws.impl;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.junyang.ws.ShowMsg;
@WebService(endpointInterface="com.junyang.ws.ShowMsg")
public class ShowMsgImp implements ShowMsg {

	@Override
	public String smg(@WebParam(name = "name") String name) {
		return "hello,"+name;
	}

}
