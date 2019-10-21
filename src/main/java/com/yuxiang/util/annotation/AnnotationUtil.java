package com.yuxiang.util.annotation;

import com.yuxiang.util.annotation.exception.AnnotationNotFoundException;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @author bydyx
 * @date 2019/10/21 10:13
 */
public class AnnotationUtil {
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
