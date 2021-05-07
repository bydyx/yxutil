package com.bydyx.string;

import lombok.Data;

/**
 * @author 风清扬
 * @date 2020/10/20 14:48
 */
@Data
public class StringPosition {
	String str;
	Integer startIndex;
	Integer endIndex;

	public StringPosition(String str, Integer startIndex) {
		this.str = str;
		this.startIndex = startIndex;
		this.endIndex = startIndex + str.length() - 1;
	}
}