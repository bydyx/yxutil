package com.bydyx.dbutil.model.db;

import com.bydyx.dbutil.model.annotation.DbTable;
import com.bydyx.dbutil.model.annotation.DbTableId;
import com.bydyx.dbutil.model.enums.TableIdTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 风清扬
 * @date 2020/10/23 16:44
 */
@Data
@Accessors(chain = true)
@DbTable("information_schema.TABLES")
public class Table {
	@DbTableId(type = TableIdTypeEnum.input)
	String tableName;
	String comment;
	String schema;
	@com.bydyx.dbutil.model.annotation.DbColumn(exist = false)
	String prefix;
	@com.bydyx.dbutil.model.annotation.DbColumn(exist = false)
	List<DbColumn> columnList;
}