package com.junyang.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;

import com.junyang.security.menu.model.Menu;

public class JsonUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	
	public static String Object2Json(Object object){
		StringBuffer strBuffer = new StringBuffer("");
		try {
			strBuffer.append(objectMapper.writeValueAsString(object));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strBuffer.toString();
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
