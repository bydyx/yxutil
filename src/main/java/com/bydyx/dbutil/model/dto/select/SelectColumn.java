package com.bydyx.dbutil.model.dto.select;

import com.bydyx.string.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 风清扬
 * @date 2020/11/27 14:54
 */
@Data
@AllArgsConstructor
public class SelectColumn {
	String columnName;
	String aliasName;

	public SelectColumn() {
		this.aliasName = Constant.space;
	}

	public String createSelectColumn() {
		return columnName + Constant.space + aliasName;
	}
}