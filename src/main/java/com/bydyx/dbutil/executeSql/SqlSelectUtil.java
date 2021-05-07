package com.bydyx.dbutil.executeSql;

import com.bydyx.dbutil.exception.DBExceptoin;
import com.bydyx.exception.UtilException;
import com.bydyx.reflex.ReflexUtil;
import com.bydyx.string.PrintUtil;
import com.bydyx.string.StringUtil;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 风清扬
 * @date 2020/11/18 16:29
 */
@AllArgsConstructor
public class SqlSelectUtil {
	Statement statement;

	public <T> T select(String sql, Class<T> returnType) {
		try {
			ResultSet resultSet = executeSimpleResultSelect(sql);
			Map<String, Method> setterMap = getSetterMap(returnType);
			ResultSetMetaData metaData = resultSet.getMetaData();
			return createEnt(resultSet, metaData, returnType, setterMap);
		} catch (Exception e) {
			throw new DBExceptoin(e);
		}
	}

	public <T> List<T> selectList(String sql, Class<T> returnType) {
		try {
			ResultSet resultSet = executeSelect(sql);
			resultSet.first();
			ArrayList<T> list = new ArrayList<>();
			Map<String, Method> setterMap = getSetterMap(returnType);
			ResultSetMetaData metaData = resultSet.getMetaData();
			logResult(resultSet, metaData);
			resultSet.first();
			do {
				list.add(createEnt(resultSet, metaData, returnType, setterMap));
			} while (resultSet.next());
			return list;
		} catch (Exception e) {
			throw new UtilException(e);
		}
	}

	private Map<String, Method> getSetterMap(Class returnType) {
		return ReflexUtil.getMethodList(returnType)
						 .stream()
						 .filter(ReflexUtil::isSetter)
						 .collect(Collectors.toMap(ReflexUtil::getSetterVariable, i -> i));
	}

	private <T> T createEnt(ResultSet resultSet, ResultSetMetaData metaData, Class<T> returnType, Map<String, Method> setterMap) throws SQLException {
		T result = ReflexUtil.instance(returnType);
		int columnCount = metaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			Method setterMethod = getSetterMethod(setterMap, metaData.getColumnName(i));
			if (Objects.nonNull(setterMethod)) {
				ReflexUtil.doMethod(result, setterMethod, resultSet.getString(i));
			}
		}
		return result;
	}

	private void logResult(ResultSet resultSet, ResultSetMetaData metaData) throws SQLException {
		if (!resultSet.wasNull()) {
			StringBuilder sb = new StringBuilder("[\n");
			do {
				sb.append("{");
				int columnCount = metaData.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					String val = resultSet.getString(i);
					sb.append("\"" + columnName + "\":\"" + val + "\",");
				}
				sb.delete(sb.length() - 1, sb.length())
				  .append("},");
			} while (resultSet.next());
			sb.delete(sb.length() - 1, sb.length())
			  .append("\n]");
			String str = sb.toString();
			str = str.replaceAll("},", "},\n");
			PrintUtil.print(str);
		}
	}

	private Method getSetterMethod(Map<String, Method> setterMap, String columnName) {
		Method method = setterMap.get(columnName);
		if (Objects.nonNull(method)) {
			return method;
		}
		method = setterMap.get(StringUtil.humpToUnderline(columnName));
		if (Objects.nonNull(method)) {
			return method;
		}
		method = setterMap.get(StringUtil.underlineToHump(columnName));
		if (Objects.nonNull(method)) {
			return method;
		}
		// COLUMN_NAME
		method = setterMap.get(StringUtil.humpToUnderline(columnName).toUpperCase());
		if (Objects.nonNull(method)) {
			return method;
		}
		return null;
	}

	private ResultSet executeSimpleResultSelect(String sql) throws SQLException {
		ResultSet resultSet = executeSelect(sql);
		resultSet.last();
		int row = resultSet.getRow();
		if (row != 1) {
			throw new DBExceptoin("符合条件的数据过多:" + row);
		}
		return resultSet;
	}

	private ResultSet executeSelect(String sql) {
		try {
			PrintUtil.print("执行sql:{}", sql);
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			throw new DBExceptoin("执行select语句失败:", e);
		}
	}

}