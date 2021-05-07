package com.bydyx.reflex;

import com.bydyx.collection.ListUtil;
import com.bydyx.collection.StreamUtil;

import java.util.List;
import java.util.Map;

/**
 * @author 风清扬
 * @date 2020/10/22 14:10
 */
public enum FieldTypeEnum {
	BASE_DATA,
	OBJECT,
	MAP,
	LIST;

	public static FieldTypeEnum getFieldTypeEnum(Class clazz) {
		if (isBaseData(clazz)) {
			return BASE_DATA;
		}
		if (isList(clazz)) {
			return LIST;
		}
		if (isMap(clazz)) {
			return MAP;
		}
		return OBJECT;
	}

	private static boolean isMap(Class clazz) {
		if (clazz.equals(Map.class)) {
			return true;
		}
		List<Class> list = ListUtil.arrayToList(clazz.getInterfaces());
		return StreamUtil.anyMatch(list, Map.class::equals);
	}

	private static boolean isList(Class clazz) {
		if (clazz.equals(List.class)) {
			return true;
		}
		List<Class> list = ListUtil.arrayToList(clazz.getInterfaces());
		return StreamUtil.anyMatch(list, List.class::equals);
	}

	private static boolean isBaseData(Class clazz) {
		if (clazz.isPrimitive()) {
			return true;
		}
		if (Byte.class.equals(clazz)) {
			return true;
		}
		if (Short.class.equals(clazz)) {
			return true;
		}
		if (Integer.class.equals(clazz)) {
			return true;
		}
		if (Long.class.equals(clazz)) {
			return true;
		}
		if (Double.class.equals(clazz)) {
			return true;
		}
		if (Float.class.equals(clazz)) {
			return true;
		}
		if (Character.class.equals(clazz)) {
			return true;
		}
		if (String.class.equals(clazz)) {
			return true;
		}
		if (CharSequence.class.equals(clazz)) {
			return true;
		}
		return false;
	}
}