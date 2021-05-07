package com.bydyx.dbutil.model.enums;

import com.bydyx.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

/**
 * @author 风清扬
 * @date 2020/10/23 17:03
 */
@Getter
@AllArgsConstructor
public enum ColumnTypeEnum implements IEnum<String> {
	Int("INT", Integer.class, false),
	TinyInt("TINYINT", Integer.class, false),
	VarChar("VARCHAR", String.class, true),
	Char("CHAR", String.class, true),
	BigInt("BIGINT", Long.class, false),
	DateTime("DATETIME", Date.class, false);

	String code;
	Class classType;
	Boolean hasLength;

}