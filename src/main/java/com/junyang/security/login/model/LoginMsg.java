package com.junyang.security.login.model;


/**
 * 
 * @author Administrator
 *
 */
public class LoginMsg {
	private boolean islogin;
	private String showMsg;
	
	public boolean isIslogin() {
		return islogin;
	}
	public void setIslogin(boolean islogin) {
		this.islogin = islogin;
	}
	public String getShowMsg() {
		return showMsg;
	}
	public void setShowMsg(String showMsg) {
		this.showMsg = showMsg;
	}
}
