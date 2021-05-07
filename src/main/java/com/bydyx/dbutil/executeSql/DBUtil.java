package com.bydyx.dbutil.executeSql;

import com.bydyx.dbutil.model.config.DbConfig;
import lombok.AllArgsConstructor;

/**
 * @author 风清扬
 * @date 2020/11/27 11:27
 */
@AllArgsConstructor
public class DBUtil {

	DbConfig dbConfig;

//	SELECT COLUMN_NAME              name,
//	DATA_TYPE                typeCode,
//	COLUMN_DEFAULT           defaultValue,
//	IS_NULLABLE              isNullAble,
//	CHARACTER_MAXIMUM_LENGTH maxLength,
//	COLUMN_KEY               primaryKeyCode,
//	COLUMN_COMMENT           comment
//	FROM information_schema.COLUMNS
//	WHERE table_name = #{tableName}
//	AND table_schema = #{schema}


}