package com.bydyx.string;

import com.bydyx.collection.ListUtil;
import com.bydyx.collection.StreamUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 风清扬
 * @date 2020/9/9 17:32
 */
public class StringUtil {
	/**
	 * 下划线后是否有字符
	 */
	private static Pattern linePattern = Pattern.compile("_(\\w)");

	public static String humpToUnderline(String source) {
		String regex = "([A-Z])";
		String result = source.replaceAll(regex, "_$1").toLowerCase();
		if (result.indexOf("_") == 0) {
			return result.substring(1);
		}
		return result;
	}

	public static String underlineToHump(String source) {
		Matcher matcher = linePattern.matcher(source.toLowerCase());
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static String humpToUnderline(String regex, String source) {
		return source.replaceAll(regex, "_$1").toLowerCase();
	}

	public static String upToHump(String str) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		String firstChar = getFirstChar(str);
		return str.replaceFirst(firstChar, StringUtils.lowerCase(firstChar));
	}

	public static String getFirstChar(String str) {
		char c = str.charAt(0);
		return c + "";
	}

	public static String createRandom(Integer total) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < total; i++) {
			int c = 'a' + (int) (Math.random() * 26);
			sb.append((char) c);
		}
		return sb.toString();
	}

	public static String concatOfComma(String... params) {
		return concat(Constant.comma, params);
	}

	public static String concatOfSpace(String... params) {
		return concat(Constant.comma, params);
	}

	public static String concat(String connector, String... params) {
		List<String> list = ListUtil.listInit(params);
		return StreamUtil.reduce(list, (s, s2) -> s + connector + s2);
	}

	public static String firstCharToLetter(String source) {
		String firstChar = getFirstChar(source);
		String s = firstChar.toLowerCase();
		return source.replaceFirst(firstChar, s);
	}

	public static String firstCharToUp(String source) {
		String firstChar = getFirstChar(source);
		String s = firstChar.toUpperCase(Locale.ROOT);
		return source.replaceFirst(firstChar, s);
	}

	public static boolean charIsLast(String str, String chars) {
		int index = str.lastIndexOf(chars);
		return index == str.length() - chars.length();
	}

	public static boolean charIsFirst(String str, String chars) {
		int index = str.indexOf(chars);
		return index == 0;
	}

	/**
	 * 拼接目录路径,并去除或增加分隔符
	 * StringUtil.concatPath("/com/fqy", "xx.txt") = /com/fqy/xx.txt
	 * StringUtil.concatPath("/com/fqy", "/xx.txt") = /com/fqy/xx.txt
	 * StringUtil.concatPath("/com/fqy/", "xx.txt") = /com/fqy/xx.txt
	 * StringUtil.concatPath("/com/fqy/", "/xx.txt") = /com/fqy/xx.txt
	 */
	public static String concatPath(String filePath, String fileName) {
		if (charIsLast(filePath, Constant.fileSeparator)) {
			if (charIsFirst(fileName, Constant.fileSeparator)) {
				return filePath + fileName.substring(1);
			} else {
				return filePath + fileName;
			}
		}
		if (charIsFirst(fileName, Constant.fileSeparator)) {
			return filePath + fileName;
		} else {
			return filePath + Constant.fileSeparator + fileName;
		}
	}

	/**
	 * 返回endStr之前的字符串
	 */
	public static String subStrBefore(String str, String endStr) {
		int index = str.indexOf(endStr);
		if (index == -1) {
			return str;
		}
		return str.substring(0, index);
	}

	public static String subStrBetween(String str, String startStr, String endStr) {
		int startIndex = str.indexOf(startStr);
		if (startIndex == -1) {
			startIndex = 0;
		} else {
			startIndex++;
		}
		int endIndex = str.indexOf(endStr);
		if (endIndex == -1) {
			endIndex = str.length();
		}
		return str.substring(startIndex, endIndex);
	}
}
