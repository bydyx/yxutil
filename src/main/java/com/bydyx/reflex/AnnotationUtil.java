package com.bydyx.reflex;

import com.bydyx.exception.UtilException;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

/**
 * @author 风清扬
 * @date 2020/12/1 14:26
 */
public class AnnotationUtil {

	public static <T extends Annotation> boolean fieldHasAnnotation(Field field, Class<T> annotationClass) {
		field.setAccessible(true);
		T fieldAnnotation = getFieldAnnotation(field, annotationClass);
		return fieldAnnotation != null;
	}

	public static <T extends Annotation> T getFieldAnnotation(Field field, Class<T> annotationClass) {
		field.setAccessible(true);
		return field.getAnnotation(annotationClass);
	}

	public static <T extends Annotation> boolean objHasAnnotation(Object target, Class<T> annotationClass) {
		Class<?> aClass = target.getClass();
		return aClass.isAnnotationPresent(annotationClass);
	}

	public static <T extends Annotation> T getAnnotation(Object target, Class<T> clazz) {
		return getAnnotation(target.getClass(), clazz);
	}

	public static <T extends Annotation> T getAnnotation(Class targetClass, Class<T> clazz) {
		Annotation annotation = targetClass.getAnnotation(clazz);
		if (annotation == null) {
			throw new UtilException(targetClass.getName() + " 不存在注解: " + clazz.getName());
		}
		return clazz.cast(annotation);
	}

	public static <T extends Annotation> boolean annotatedElementHasAnnotation(AnnotatedElement target, Class<T> clazz) {
		try {
			getAnnotation(target, clazz);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static <T extends Annotation> T getAnnotation(AnnotatedElement target, Class<T> clazz) {
		T annotation = target.getAnnotation(clazz);
		if (annotation == null) {
			throw new UtilException(target.getClass().getName() + " 不存在注解: " + clazz.getName());
		}
		return annotation;
	}
}