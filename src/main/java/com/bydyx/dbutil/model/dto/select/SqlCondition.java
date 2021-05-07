package com.bydyx.dbutil.model.dto.select;

import com.bydyx.dbutil.model.enums.SqlConditionRelationEnum;
import com.bydyx.dbutil.model.enums.SqlConditionTypeEnum;
import com.bydyx.exception.UtilException;
import com.bydyx.string.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 风清扬
 * @date 2020/11/27 15:07
 */
@Data
@AllArgsConstructor
public class SqlCondition {
	String columnName;
	String val;
	SqlConditionRelationEnum sqlConditionRelation;
	SqlConditionTypeEnum sqlConditionType;

	public String getVal() {
		switch (sqlConditionRelation) {
			case 大于:
			case 大于等于:
			case 小于:
			case 小于等于:
			case 等于:
			case 不等于:
				return val;
			case 不在这个范围:
			case 在这个范围:
				return "(" + val + ")";
			case 模糊匹配:
				return "LIKE CONCAT('%'," + val + ",'%')";
		}
		throw new UtilException("怎么可能?");
	}

	public String createDesc() {
		return sqlConditionType.name() + Constant.space + columnName + Constant.space + sqlConditionRelation.getSign() + getVal();
	}

}