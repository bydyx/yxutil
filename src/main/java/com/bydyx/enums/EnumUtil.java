package com.bydyx.enums;

import com.bydyx.collection.ListUtil;
import com.bydyx.collection.StreamUtil;
import com.bydyx.exception.UtilParamException;
import com.bydyx.reflex.ReflexUtil;
import com.bydyx.string.PrintUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 风清扬
 * @date 2020/9/18 17:50
 */
public class EnumUtil {

    static Map<String, Map<Object, IEnum<Object>>> iEnumContainer = new HashMap<>();

    public static <E extends IEnum<T>, T> E getEnum(Class<E> clazz, T sign) {
        Map<T, IEnum<T>> enumMap = getTiEnumMap(clazz);
        IEnum<T> tiEnum = enumMap.get(sign);
        if (Objects.isNull(tiEnum)) {
            throw new UtilParamException(PrintUtil.print("class:{} 不存在的sign: {}", clazz.getName(), sign));
        }
        return (E) tiEnum;
    }

    private static <E extends IEnum, T> Map<T, IEnum<T>> getTiEnumMap(Class<E> clazz) {
        Map<T, IEnum<T>> enumMap = (HashMap) iEnumContainer.get(clazz.getName());
        if (Objects.isNull(enumMap)) {
            enumMap = StreamUtil.listToMap(getEnumValueList(clazz), IEnum::getCode);
            iEnumContainer.put(clazz.getName(), (Map) enumMap);
        }
        return enumMap;
    }

    public static <E extends IEnum> List<E> getEnumValueList(Class<? extends IEnum> clazz) {
        E[] values = (E[]) ReflexUtil.doNoParamStaticMethod(clazz, "values");
        return ListUtil.arrayToList(values);
    }
}