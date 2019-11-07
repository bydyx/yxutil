package com.bydyx.yxutil.help;

import com.bydyx.yxutil.help.model.NoRequire;
import com.bydyx.yxutil.reflex.ClassUtil;
import com.bydyx.yxutil.string.StringUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字符串工具,不应在程序中调用,只适合在test中调用
 * 例如 生成mybatis的insert语句
 *
 * @author qiang.feng
 * @date 2019/11/1 16:25
 */
public class MybatisUtil {
    /**
     * target.field加@Require,会生成相应的mybatisInsert语句
     *
     * @param clazz
     * @return
     */
    public void createInsert(Class clazz) {
        String tableName = clazz.getName();
        tableName = StringUtil.lastCharToEndNoCh(tableName, ".").toLowerCase();
        List<Field> fieldList = ClassUtil.getFieldNoAnnotation(clazz, NoRequire.class);
        List<String> fieldNameList = fieldList.stream().map(Field::getName).collect(Collectors.toList());
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        String sqlColumn = fieldNameList.stream()
                                        .reduce((prev, curr) -> prev + "," + curr)
                                        .orElseThrow(() -> new RuntimeException("NotFoundField"));
        sql.append(sqlColumn).append(")\nVALUES (");
        String sqlValue = fieldNameList.stream()
                                       .map(item -> "#{" + item + "}")
                                       .reduce((prev, curr) -> prev + "," + curr)
                                       .orElseThrow(() -> new RuntimeException("NotFoundField"));
        sql.append(sqlValue).append(")");
        System.out.println(sql);
    }
}