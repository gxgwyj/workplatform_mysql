package com.junyang.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

import com.junyang.security.model.Menu;

public class JsonUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	public static String Object2Json(Object object){
		StringBuffer strBuffer = new StringBuffer("");
		try {
			objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat(dateFormat));
			strBuffer.append(objectMapper.writeValueAsString(object));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strBuffer.toString();
	}
	public static String getDateFormat() {
		return dateFormat;
	}
	public static void setDateFormat(String dateFormat) {
		JsonUtil.dateFormat = dateFormat;
	}
	public static void main(String[] args) {
		ArrayList<Menu>  menuList = new ArrayList<Menu>();
		for(int i =0;i<5;i++){
			Menu menu = new Menu();
			menu.setId(String.valueOf(i));
			menu.setName("menu"+i);
			menu.setPid(String.valueOf(i-1));
			menu.setTitle("title"+i);
			menuList.add(menu);
		}
		System.out.println(Object2Json(menuList));
		String json = Object2Json(menuList);
		

	}

}
