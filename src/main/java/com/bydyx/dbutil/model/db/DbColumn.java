package com.bydyx.dbutil.model.db;

import com.bydyx.dbutil.model.enums.DateTypeEnum;
import com.bydyx.string.StringUtil;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 风清扬
 * @date 2020/10/23 16:45
 */
@Data
@Accessors(chain = true)
public class DbColumn {
	String columnName;
	// int(11)
	String columnType;
	// NO |YES
	String isNullable;
	// PRI | 空串
	String columnKey;
	// 默认值
	String columnDefault;
	// 主键的值为auto_incremen，其余皆为空串
	String extra;
	// 注释
	String columnComment;

	public boolean isNullable() {
		return isNullable.equals("YES");
	}

	public boolean isPrimaryKey() {
		return columnKey.equals("PRI");
	}

	public boolean isAutoIncrement() {
		return extra.equals("auto_increment");
	}

	public String returnType() {
		return StringUtil.subStrBefore(columnType, "(");
	}

	public int returnMaxLength() {
		if (isDateType()) {
			return 0;
		}
		String maxLength = StringUtil.subStrBetween(columnType, "(", ")");
		return Integer.valueOf(maxLength);
	}

	public boolean isDateType() {
		return DateTypeEnum.isDateType(columnType);
	}
}