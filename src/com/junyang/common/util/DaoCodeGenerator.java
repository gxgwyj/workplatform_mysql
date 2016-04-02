package com.junyang.common.util;

import java.io.IOException;

public class DaoCodeGenerator {
	public final static String CMD = "java -jar mybatis-generator-core-1.3.2.jar -configfile generatorConfig.xml -overwrite";
	public static void main(String[] args) {
		Runtime runtime=Runtime.getRuntime();
		try {
			runtime.exec("cmd /c start C://a.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
