package com.junyang.common;

public class Constants {
	public static final  String TURN_PAGE = "turnPage";//翻页标识
	public static final  String P_MASTER_ROLE = "role";
	public static final  String P_MASTER_USER = "user";
	public static final  String SESSION_USER = "sessionUser";
	public static final  String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)).*";	//不对匹配该值的访问路径拦截（正则）

}
