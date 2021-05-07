package com.bydyx.dbutil.executeSql;


import com.bydyx.dbutil.model.config.DbConfig;
import com.bydyx.dbutil.model.db.DbColumn;
import com.bydyx.dbutil.model.db.Table;
import com.bydyx.dbutil.model.dto.select.SelectColumn;
import com.bydyx.dbutil.model.dto.select.SelectSql;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 风清扬
 * @date 2020/11/18 16:06
 */
public class ExecuteSqlUtil {

	DbConfig dbConfig;
	Statement statement;


	public ExecuteSqlUtil(DbConfig dbConfig) {
		this.dbConfig = dbConfig;
		this.statement = ConnectionFactory.createStatement(dbConfig);
	}

	public static ExecuteSqlUtil create(DbConfig config) {
		return new ExecuteSqlUtil(config);
	}

	public List<DbColumn> selectAllColumnByTable(String tableName) {
		String sql = "show full columns from " + tableName + ";";
		return this.selectList(sql, DbColumn.class);
	}

	public <T> T select(String sql, Class<T> returnType) {
		SqlSelectUtil sqlSelectUtil = new SqlSelectUtil(statement);
		return sqlSelectUtil.select(sql, returnType);
	}

	public <T> List<T> selectList(String selectSql, Class<T> returnType) {
		SqlSelectUtil sqlSelectUtil = new SqlSelectUtil(statement);
		return sqlSelectUtil.selectList(selectSql, returnType);
	}

	public List<Table> getTableList() {
		SelectSql selectSql = new SelectSql("information_schema.TABLES")
			.setColumnList(createColumnList())
			.eq("TABLE_SCHEMA", dbConfig.getSchema());
		String sql = selectSql.createSql();
		return ExecuteSqlUtil.create(dbConfig).selectList(sql, Table.class);
	}

	List<SelectColumn> createColumnList() {
		return new ArrayList<SelectColumn>() {{
			add(new SelectColumn("TABLE_NAME", "tableName"));
			add(new SelectColumn("TABLE_COMMENT", "comment"));
		}};
	}
}