package com.bydyx.collection;

import com.bydyx.collection.function.ObjSetFunction;
import com.bydyx.exception.UtilException;
import com.bydyx.string.Constant;
import com.bydyx.string.PrintUtil;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author q.f
 * @date 2020/7/23 18:05
 */
public class StreamUtil {

	/**
	 * list<T>映射成另一种list<R>
	 *
	 * @param <T> 输入类型
	 * @param <R> 输出类型
	 * @date 2020/7/28 13:08
	 * @author f.q
	 */
	public static <T, R> List<R> map(List<T> list, Function<T, R> mapFunction) {
		return list.stream()
				   .map(mapFunction)
				   .collect(Collectors.toList());
	}

	/**
	 * list<T>转Map<K,T>
	 *
	 * @param <T> 输入类型
	 * @param <K> map的key
	 * @date 2020/7/28 13:09
	 * @author f.q
	 */
	public static <T, K> Map<K, T> listToMap(List<T> list, Function<T, K> keyMapper) {
		return listToMap(list, keyMapper, t -> t);
	}

	/**
	 * list<T>转Map<K,V>
	 *
	 * @param <T> 输入类型
	 * @param <K> map的key
	 * @param <V> map的value
	 * @date 2020/7/28 13:09
	 * @author f.q
	 */
	public static <T, K, V> Map<K, V> listToMap(List<T> list,
												Function<T, K> keyMapper,
												Function<T, V> valueMapper
	) {
		return list.stream()
				   .collect(Collectors.toMap(keyMapper, valueMapper, (v, v2) -> v2));
	}

	/**
	 * list分组
	 *
	 * @param <T> 被分组的类型
	 * @param <K> 分组的标志
	 * @date 2020/7/28 13:11
	 * @author f.q
	 */
	public static <T, K> Map<K, List<T>> groupBy(List<T> list, Function<T, K> classFun) {
		return list.stream()
				   .collect(Collectors.groupingBy(classFun));
	}

	/**
	 * @param classFun classFun的返回结果是分类的依据 K
	 * @param mapper   mapper的返回结果是分组之后的具体元素 V
	 * @param <T>      被分组的类型
	 * @param <K>      分组的标志
	 * @param <V>      分组之后的结果类型
	 * @date 2020/7/28 13:11
	 * @author f.q
	 */
	public static <T, K, V> Map<K, List<V>> groupByValList(List<T> list, Function<T, K> classFun, Function<T, V> mapper) {
		return list.stream()
				   .collect(Collectors.groupingBy(classFun, Collectors.mapping(mapper, Collectors.toList())));
	}

	public static <T, C, K> Map<C, Map<K, T>> groupByValMap(List<T> list, Function<T, C> classFun, Function<T, K> keyFun) {
		return groupByValMap(list, classFun, keyFun, t -> t);
	}

	public static <T, C, K, V> Map<C, Map<K, V>> groupByValMap(List<T> list, Function<T, C> classFun, Function<T, K> keyFun, Function<T, V> valFun) {
		return list.stream()
				   .collect(Collectors.groupingBy(classFun, Collectors.mapping(t -> t, Collectors.toMap(keyFun, valFun))));
	}

	public static <T> T reduce(List<T> list, BinaryOperator<T> operator) {
		if (list.isEmpty()) {
			PrintUtil.print("需要聚合的list为空!");
			return null;
		}
		if (Constant.one.equals(list.size())) {
			return list.get(Constant.zero);
		}
		return list.stream()
				   .reduce(operator)
				   .get();
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
		return list.stream()
				   .filter(predicate)
				   .collect(Collectors.toList());
	}

	public static <T> Set<T> filter(Set<T> list, Predicate<T> predicate) {
		return list.stream()
				   .filter(predicate)
				   .collect(Collectors.toSet());
	}

	public static <T> List<T> foreach(List<T> list, Consumer<T> consumer) {
		list.forEach(consumer);
		return list;
	}

	public static <T> boolean anyMatch(List<T> list, Predicate<T> predicate) {
		return list.stream()
				   .anyMatch(predicate);
	}

	public static <T, K, V> List<T> setAttrByMap(List<T> list, Map<K, V> map, Function<T, K> getKey, ObjSetFunction<T, V> setVal) {
		list.forEach(item -> {
			K key = getKey.apply(item);
			V v = map.get(key);
			setVal.setVal(item, v);
		});
		return list;
	}

	public static <T, K, V> T setAttrByMap(T t, Map<K, V> map, Function<T, K> getKey, ObjSetFunction<T, V> setVal) {
		K key = getKey.apply(t);
		V v = map.get(key);
		setVal.setVal(t, v);
		return t;
	}

	public static <T> List<T> sorted(List<T> list) {
		return list.stream()
				   .sorted()
				   .collect(Collectors.toList());
	}

	public static <T> List<T> sorted(List<T> list, Comparator<? super T> comparator) {
		return list.stream()
				   .sorted(comparator)
				   .collect(Collectors.toList());
	}

	public static <T, R extends Comparable> List<T> sorted(List<T> list, Function<T, R> map) {
		return list.stream()
				   .sorted((o1, o2) -> map.apply(o1).compareTo(map.apply(o2)))
				   .collect(Collectors.toList());
	}

	public static <T, K extends Comparable> K max(List<T> list, Function<T, K> map) {
		return list.stream()
				   .map(map)
				   .max(K::compareTo)
				   .get();
	}

	public static <T, K extends Comparable> K min(List<T> list, Function<T, K> map) {
		return list.stream()
				   .map(map)
				   .min(K::compareTo)
				   .get();
	}

	public static <T, K, V> List<T> setAttrByMap(List<T> list, Map<K, V> map, Function<T, K> getKey, V defaultVal, ObjSetFunction<T, V> setVal) {
		list.forEach(item -> setAttrByMap(item, map, getKey, defaultVal, setVal));
		return list;
	}

	public static <T, K, V> T setAttrByMap(T t, Map<K, V> map, Function<T, K> getKey, V defaultVal, ObjSetFunction<T, V> setVal) {
		K key = getKey.apply(t);
		V v = map.getOrDefault(key, defaultVal);
		setVal.setVal(t, v);
		return t;
	}
}