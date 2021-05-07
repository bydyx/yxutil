package com.bydyx.reflex;

import com.bydyx.collection.ListUtil;
import com.bydyx.collection.StreamUtil;
import com.bydyx.exception.UtilException;
import com.bydyx.reflex.exception.ClassInitRtException;
import com.bydyx.string.PrintUtil;
import com.bydyx.string.RegUtil;
import com.bydyx.string.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.*;
import java.util.List;
import java.util.Objects;

/**
 * @author q.f
 * @date 2020/8/4 10:36
 */
public class ReflexUtil {

	public static boolean fieldIsClassOrList(Field field, Class parentClass) {
		Class fieldType = field.getType();
		if (ReflexUtil.isImplInterface(fieldType, parentClass)) {
			return true;
		}
		if (fieldType.equals(List.class) || ReflexUtil.isImplInterface(fieldType, List.class)) {
			ParameterizedType genericType = (ParameterizedType) field.getGenericType();
			Type typeArgument = genericType.getActualTypeArguments()[0];
			if (typeArgument.getTypeName().equals(parentClass.getName())) {
				return true;
			}
		}
		return false;
	}

	private static Class[] createClassArray(Object[] params) {
		Class[] paramsClass = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			paramsClass[i] = params[i].getClass();
		}
		return paramsClass;
	}

	public static Object getFieldVal(Field field, Object obj) {
		try {
			return field.get(obj);
		} catch (IllegalAccessException e) {
			PrintUtil.print("获取属性:{} 值出现异常:{}", field, e);
			return new Object();
		}
	}

	public static void setFieldVal(Object obj, String fieldName, Object val) {
		try {
			Field field = getField(obj, fieldName);
			field.set(obj, val);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static Field getField(Object obj, String fieldName) {
		return getField(obj.getClass(), fieldName);
	}

	public static Field getField(Class clazz, String fieldName) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field;
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	public static Object doNoParamMethod(Object obj, String methodName, Class... args) {
		Method method = getMethod(obj.getClass(), methodName, args);
		return doMethod(obj, method);
	}

	public static Object doMethod(Object obj, Method method, Object... args) {
		try {
			return method.invoke(obj, args);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new UtilException(e);
		}
	}

	public static Object doNoParamStaticMethod(Class clazz, String methodName, Class... args) {
		Method method = getMethod(clazz, methodName, args);
		return doMethod(null, method);
	}

	private static boolean methodIsStatic(Method method) {
		int modifiers = method.getModifiers();
		return MethodModifierEnum.hasModifier(modifiers, MethodModifierEnum.STATIC);
	}

	public static Method getMethod(Class clazz, String methodName, Class... args) {
		try {
			return clazz.getMethod(methodName, args);
		} catch (NoSuchMethodException e) {
			throw new UtilException(e);
		}
	}

	public static List<Field> getFieldList(Class clazz) {
		Field[] declaredFields = clazz.getDeclaredFields();
		List<Field> fieldList = ListUtil.arrayToList(declaredFields);
		List<Field> resultList = StreamUtil.foreach(fieldList, ReflexUtil::setFieldAccessible);
		Class superClass = clazz.getSuperclass();
		if (Objects.nonNull(superClass) && !superClass.equals(Object.class)) {
			List<Field> supperFieldList = getFieldList(superClass);
			resultList = ListUtil.merge(resultList, supperFieldList);
		}
		return resultList;
	}

	private static void setFieldAccessible(Field field) {
		field.setAccessible(true);
	}

	public static boolean isImplInterface(Class clazz, Class interfaceClass) {
		List<Class> list = ListUtil.arrayToList(clazz.getInterfaces());
		if (StreamUtil.anyMatch(list, interfaceClass::equals)) {
			return true;
		}
		if (StreamUtil.anyMatch(list, item -> isImplInterface(item, interfaceClass))) {
			return true;
		}
		return false;
	}

	public static boolean isExtends(Class clazz, Class fatherClass) {
		if (clazz.isAssignableFrom(fatherClass)) {
			return true;
		}
		if (isImplInterface(clazz, fatherClass)) {
			return true;
		}
		return false;
	}

	public static List<Method> getMethodList(Class clazz) {
		Method[] declaredMethods = clazz.getDeclaredMethods();
		List<Method> methodList = ListUtil.arrayToList(declaredMethods);
		methodList.forEach(item -> item.setAccessible(true));
		return methodList;
	}

	public static boolean isSetter(Method method) {
		String name = method.getName();
		String reg = "set[A-Z]{1}\\w*";
		if (!RegUtil.matches(reg, name)) {
			return false;
		}
		int parameterCount = method.getParameterCount();
		if (parameterCount != 1) {
			return false;
		}
		return true;
	}

	public static String getSetterVariable(Method method) {
		String name = method.getName();
		String substring = name.substring(3);
		return StringUtil.firstCharToLetter(substring);
	}

	public static <T> T instance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new UtilException(e);
		}
	}

	public static <T> T instance(Class<T> tClazz, Object... params) {
		try {
			Class[] paramsClass = createClassArray(params);
			Constructor<T> constructor = tClazz.getConstructor(paramsClass);
			constructor.setAccessible(true);
			return constructor.newInstance(paramsClass);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new ClassInitRtException(e);
		}
	}

}