package com.bydyx.collection.function;

/**
 * @author 风清扬
 * @date 2021/3/23 16:37
 */
@FunctionalInterface
public interface ObjSetFunction<T, V> {
	void setVal(T t, V v);
}