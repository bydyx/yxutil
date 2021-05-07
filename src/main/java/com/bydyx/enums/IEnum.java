package com.bydyx.enums;

import com.bydyx.reflex.ReflexUtil;

import java.util.List;

/**
 * @author 风清扬
 * @date 2020/9/18 18:00
 */
public interface IEnum<T> {
	default T getCode() {
		return (T) name();
	}

	String name();

	default boolean equalsCode(T code) {
		return getCode().equals(code);
	}

}
