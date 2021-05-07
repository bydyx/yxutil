package com.bydyx.dbutil.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SqlConditionRelationEnum {
	等于("="),
	不等于("<>"),
	模糊匹配("LIKE"),
	大于(">"),
	大于等于(">="),
	小于("<"),
	小于等于("<="),
	在这个范围("IN"),
	不在这个范围("NOT IN")
	;
	@Getter
	String sign;
}