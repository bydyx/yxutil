package com.bydyx.dbutil.model.config;

import com.bydyx.dbutil.model.enums.DbType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 风清扬
 * @date 2020/11/18 15:46
 */
@Data
@AllArgsConstructor
public class DbConfig {
	Integer port;
	String schema;
	String address;
	String username;
	String password;
	DbType dbType;
}