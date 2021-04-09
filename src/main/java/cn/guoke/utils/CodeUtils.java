package cn.guoke.utils;


import cn.hutool.core.util.RandomUtil;

public class CodeUtils {

	
	public static String getCode() {
		return RandomUtil.randomString(4);
	}
	

}
