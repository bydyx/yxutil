package com.bydyx.dbutil.executeSql;


import com.bydyx.dbutil.exception.DBExceptoin;
import com.bydyx.dbutil.model.config.DbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author 风清扬
 * @date 2020/11/18 15:44
 */
public class ConnectionFactory {

	public static Statement createStatement(DbConfig config) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(createJdbcUrl(config), config.getUsername(), config.getPassword());
			return connection.createStatement();
		} catch (Exception e) {
			throw new DBExceptoin("获取connection出错:", e);
		}
	}

	static String createJdbcUrl(DbConfig config) {
		switch (config.getDbType()) {
			case MYSQL:
				return "jdbc:mysql://" + config.getAddress() + ":" + config.getPort() + "/" + config.getSchema();
		}
		throw new DBExceptoin();
	}
}