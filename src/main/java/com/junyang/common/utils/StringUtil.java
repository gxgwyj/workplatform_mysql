package com.junyang.common.utils;
/**
 * 字符串处理工具
 * @author Administrator
 *
 */
public class StringUtil {
	/**
	 * 判断字符串是否为空，包含null
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(final String str){
		boolean isEmpty = false;
		if(null==str||"".equals(str.trim())){
			isEmpty = true;
		}
		return isEmpty;
	}

}
