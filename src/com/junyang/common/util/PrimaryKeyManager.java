package com.junyang.common.util;

import java.util.UUID;

public class PrimaryKeyManager {
	public static String getPrimaryKey() {
		UUID uuid = java.util.UUID.randomUUID();
		String keyStr = uuid.toString().replaceAll("-", "");
		return keyStr;
	}

	public static void main(String[] args) {
		System.out.println(getPrimaryKey());
		System.out.println(getPrimaryKey().length());
	}
	
	
}
