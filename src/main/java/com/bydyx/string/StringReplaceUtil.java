package com.bydyx.string;

import com.bydyx.collection.ListUtil;
import com.bydyx.collection.StreamUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 风清扬
 * @date 2020/10/20 14:48
 */
public class StringReplaceUtil {
	public static List<StringPosition> getPlaceHolderList(String source, String reg) {
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(source);
		List<StringPosition> list = new ArrayList<>();
		while (matcher.find()) {
			String group = matcher.group();
			int start = matcher.start();
			list.add(new StringPosition(group, start));
		}
		return list;
	}

	public static String replace(String source, String reg, Map<String, String> keyValue) {
		List<String> strList = StreamUtil.map(getPlaceHolderList(source, reg), StringPosition::getStr);
		for (String item : strList) {
			String value = keyValue.get(item);
			source = source.replace(item, value);
		}
		return source;
	}

}