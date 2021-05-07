package com.bydyx.dbutil.model.annotation;

/**
 * 字段
 *
 * @author 风清扬
 * @date 2020/12/1 09:11
 */
public @interface DbColumn {
	/**
	 * 字段名
	 */
	String value() default "";

	/**
	 * 是否为数据库表字段
	 */
	boolean exist() default true;
}
