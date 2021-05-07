package com.bydyx.dbutil.model.dto.select;

import com.bydyx.collection.StreamUtil;
import com.bydyx.dbutil.model.enums.SqlConditionRelationEnum;
import com.bydyx.dbutil.model.enums.SqlConditionTypeEnum;
import com.bydyx.string.Constant;
import com.bydyx.string.StringUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * select 的参数
 *
 * @author 风清扬
 * @date 2020/11/27 14:52
 */
@Data
@Accessors(chain = true)
public class SelectSql {
	List<SelectColumn> columnList;
	String tableName;
	List<SqlCondition> conditionList;
	List<SelectOrder> orderList;

	SqlConditionTypeEnum sqlConditionType;

	public SelectSql(String tableName) {
		this.tableName = tableName;
		this.orderList = new ArrayList<>();
		this.columnList = new ArrayList<>();
		this.conditionList = new ArrayList<>();
		this.sqlConditionType = SqlConditionTypeEnum.AND;
	}

	public static SelectSql create(Object obj) {
		return null;
	}

	public SelectSql eq(String columnName, Object val) {
		appendCondition(columnName, val, SqlConditionRelationEnum.等于);
		return this;
	}

	public SelectSql like(String columnName, Object val) {
		appendCondition(columnName, val, SqlConditionRelationEnum.模糊匹配);
		return this;
	}

	public SelectSql appendCondition(String columnName, Object val, SqlConditionRelationEnum sqlConditionRelation) {
		String valDesc = createVal(val);
		conditionList.add(new SqlCondition(columnName, valDesc, sqlConditionRelation, sqlConditionType));
		return this;
	}

	private String createVal(Object val) {
		if (val.getClass().equals(String.class)) {
			return Constant.singleQuotes + val.toString() + Constant.singleQuotes;
		}
		return val.toString();
	}

	public SelectSql or() {
		sqlConditionType = SqlConditionTypeEnum.OR;
		return this;
	}

	public SelectSql and() {
		sqlConditionType = SqlConditionTypeEnum.AND;
		return this;
	}

	public String createSql() {
		StringBuilder sql = new StringBuilder("SELECT ");
		if (columnList.isEmpty()) {
			sql.append(" * ");
		} else {
			List<String> selectColumnList = StreamUtil.map(columnList, SelectColumn::createSelectColumn);
			String columnDesc = StreamUtil.reduce(selectColumnList, StringUtil::concatOfComma);
			sql.append(columnDesc);
		}
		sql.append(" FROM ").append(tableName);
		if (!conditionList.isEmpty()) {
			sql.append(" WHERE ");
			String conditionDesc = StreamUtil.reduce(StreamUtil.map(conditionList, SqlCondition::createDesc), StringUtil::concatOfSpace);
			// 去除第一个条件的and｜or
			sql.append(conditionDesc.substring(3));
		}
		return sql.toString();
	}
}