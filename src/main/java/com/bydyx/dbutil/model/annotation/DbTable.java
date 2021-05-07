package com.bydyx.dbutil.model.annotation;

/**
 * @author 风清扬
 * @date 2020/11/30 14:50
 */
public @interface DbTable {
	/**
	 * 表名
	 */
	String value() default "";
}
