package com.bydyx.dbutil.model.enums;

public enum DateTypeEnum {
	datetime;

	public static boolean isDateType(String columnType) {
		DateTypeEnum[] values = values();
		for (final DateTypeEnum value : values) {
			if (value.name().equals(columnType)) {
				return true;
			}
		}
		return false;
	}
}