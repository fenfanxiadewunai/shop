package com.huang.util;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class FileTypeAllowUtil {
	private static Properties properties = new Properties();
	
	static{
		try{
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("filetype.properties"));
		}catch(Exception e){}
	}
	
	public static boolean validateFileType(String suff,String fileType){
		if(!properties.containsKey(suff))return false;
		String[] values = properties.get(suff).toString().split(",");
		List<String> allowType = Arrays.asList(values);
		return allowType.contains(fileType);
	}
}
