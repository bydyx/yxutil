package com.bydyx.string;

import com.alibaba.fastjson.JSON;
import com.bydyx.collection.ListUtil;
import com.bydyx.collection.QueueUtil;
import com.bydyx.collection.StreamUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Queue;

/**
 * @author q.f
 * @date 2020/8/4 9:34
 */
public class PrintUtil {

	public static String printJsonStr(Object obj) {
		String str = JSON.toJSONString(obj);
		return printBase(str);
	}

	public static String print(String str, Object... params) {
		List<String> strList = ListUtil.arrayToList(str.split("\\{\\}"));
		if (str.lastIndexOf("{}") == str.length() - 2) {
			strList.add("");
		}
		if (params.length == 0 || strList.size() == 1) {
			return printBase(str);
		}
		Queue<Object> paramQueue = QueueUtil.queueLinkedListInit(params);
		while (strList.size() > paramQueue.size()) {
			paramQueue.offer("{}");
		}
		String reduce = StreamUtil.reduce(strList, (s, s2) -> StringUtils.join(s, paramQueue.poll(), s2));
		return printBase(reduce);
	}

	public static String print(Exception e) {
		e.printStackTrace();
		return printBase(e.getMessage());
	}

	public static void print(Object[] objs) {
		for (int i = 0; i < objs.length; i++) {
			printBase(objs[i]);
		}
	}

	public static String print(Object obj) {
		return printBase(obj);
	}

	public static void print(List list) {
		printBase(JSON.toJSONString(list));
	}

	public static String printJson(Object obj) {
		return printBase(JSON.toJSONString(obj));
	}

	public static void printList(String str, List list) {
		list.stream()
			.map(s -> str + s)
			.forEach(PrintUtil::printBase);
	}

	private static String printBase(Object obj) {
		Thread currentThread = Thread.currentThread();
		StackTraceElement stackTrace = currentThread.getStackTrace()[3];
		String prefix = "fqy." + currentThread.getName() + "(" + stackTrace.getFileName() + ":" + stackTrace.getLineNumber() + ") ";
		System.out.println(prefix + obj);
		return String.valueOf(obj);
	}
}
