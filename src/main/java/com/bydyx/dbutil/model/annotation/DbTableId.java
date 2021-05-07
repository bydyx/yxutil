package com.bydyx.dbutil.model.annotation;


import com.bydyx.dbutil.model.enums.TableIdTypeEnum;

/**
 * 主键
 * @author 风清扬
 * @date 2020/11/30 16:51
 */
public @interface DbTableId {
	/**
	 * id名称
	 */
	String value() default "";

	TableIdTypeEnum type() default TableIdTypeEnum.autoIncrement;
}