package com.junyang.common.utils;

import java.util.UUID;

public class PrimaryKeyManager {
	public static String getPrimaryKey() {
		UUID uuid = java.util.UUID.randomUUID();
		String keyStr = uuid.toString().replaceAll("-", "");
		System.out.println(keyStr);
		return keyStr;
	}

	public static void main(String[] args) {
		System.out.println(getPrimaryKey());
		System.out.println(getPrimaryKey().length());
	}
	
	
}
