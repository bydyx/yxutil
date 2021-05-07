package com.bydyx.string;

import java.util.regex.Pattern;

/**
 * @author 风清扬
 * @date 2020/11/18 20:23
 */
public class RegUtil {

	public static boolean matches(String reg,String str) {
		return Pattern.matches(reg,str);
	}
}