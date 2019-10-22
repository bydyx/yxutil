package com.bydyx.yxutil.annotation;

import com.bydyx.yxutil.annotation.exception.AnnotationNotFoundException;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

/**
 * @author bydyx
 * @date 2019/10/21 10:13
 */
public class AnnotationUtil {

    public static <T extends Annotation> boolean fieldHasAnnotation(Field field, Class<T> annotationClass) {
        field.setAccessible(true);
        T annotation = field.getAnnotation(annotationClass);
        return annotation != null;
    }

    public static <T extends Annotation> boolean objHasAnnotation(Object target, Class<T> annotationClass) {
        Class<?> aClass = target.getClass();
        return aClass.isAnnotationPresent(annotationClass);
    }

    public static <T extends Annotation> T getAnnotation(Class target, Class<T> clazz) {
        Annotation annotation = target.getAnnotation(clazz);
        if (annotation == null) {
            throw new AnnotationNotFoundException(target.getName() + " 不存在: " + clazz.getName());
        }
        return clazz.cast(annotation);
    }

    public static <T extends Annotation> T getAnnotation(AnnotatedElement target, Class<T> clazz) {
        T annotation = target.getAnnotation(clazz);
        if (annotation == null) {
            throw new AnnotationNotFoundException(target.getClass().getName() + " 不存在: " + clazz.getName());
        }
        return annotation;
    }
}