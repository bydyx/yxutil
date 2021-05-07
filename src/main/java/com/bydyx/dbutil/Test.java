package com.bydyx.dbutil;

import com.bydyx.collection.StreamUtil;
import com.bydyx.dbutil.executeSql.ExecuteSqlUtil;
import com.bydyx.dbutil.model.config.DbConfig;
import com.bydyx.dbutil.model.db.DbColumn;
import com.bydyx.dbutil.model.enums.DbType;
import com.bydyx.string.PrintUtil;

import java.util.List;

/**
 * @author 风清扬
 * @date 2021/3/1 14:57
 */
public class Test {
	public static final String address = "39.106.184.95";
	public static final String username = "root";
	public static final String schema = "back";
	public static final String password = "qiang136244.";
	public static final Integer port = 3306;
//
//	public static void main(String[] args) {
//		ExecuteSqlUtil executeSqlUtil = ExecuteSqlUtil.create(dbConfig);
//		List<Table> tableList = executeSqlUtil.getTableList();
//		PrintUtil.printList(tableList);
//	}

	public static void main(String[] args) {
		ExecuteSqlUtil executeSqlUtil = createExecuteSqlUtil();
		List<DbColumn> list = executeSqlUtil.selectAllColumnByTable("article");
	}

	static ExecuteSqlUtil createExecuteSqlUtil() {
		DbConfig dbConfig = new DbConfig(port, schema, address, username, password, DbType.MYSQL);
		return ExecuteSqlUtil.create(dbConfig);
	}
}