package com.junyang.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.junyang.ws.ShowMsg;

public class ConnectServer {

	public static void main(String[] args) {
		// 调用WebService  
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
        factory.setServiceClass(ShowMsg.class);  
        factory.setAddress("http://localhost:7001/OA/ws/services/showMsgService");  
  
        ShowMsg showMsgService = (ShowMsg) factory.create();  
        System.out.println("invokeResult："+showMsgService.smg("周杰伦"));
	}

}
