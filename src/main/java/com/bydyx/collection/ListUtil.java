package com.bydyx.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author q.f
 * @date 2020/8/4 9:55
 */
public class ListUtil {
	public static final ArrayList EmptyList = new ArrayList();

	public static <T> List<T> listInit(T... params) {
		return new ArrayList(params.length) {{
			for (final T param : params) {
				add(param);
			}
		}};
	}

	public static <T> List<T> arrayToList(T[] attr) {
		return new ArrayList<T>() {{
			for (T t : attr) {
				add(t);
			}
		}};
	}
	/**
	 * 新建含有一个子元素的list
	 */
	public static <T> List<T> createList(T t) {
		ArrayList<T> list = new ArrayList<>();
		list.add(t);
		return list;
	}

	public static <T> List<T> merge(List<T>... lists) {
		List<T> result = new ArrayList<>();
		for (int i = 0; i < lists.length; i++) {
			result.addAll(lists[i]);
		}
		return result;
	}

	/**
	 * 如果list是null,那么创建一个空的list
	 */
	public static <T> List<T> listOrNullCreateArrayList(List<T> list) {
		if (list == null) {
			return new ArrayList<>();
		}
		return list;
	}

	public static <T> List<T> appendItem(List<T> list, T t) {
		list.add(t);
		return list;
	}

	public static <T> HashSet<T> toHashSet(List<T> list) {
		return new HashSet<>(list);
	}

}