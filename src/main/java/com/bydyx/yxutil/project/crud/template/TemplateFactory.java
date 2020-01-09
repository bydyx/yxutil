package com.bydyx.yxutil.project.crud.template;

/**
 * @author qiang.feng
 * @date 2020/1/9 11:20
 */
public class TemplateFactory {

    public static Template getFileTemplate(ModuleType type, CrudConfig crudConfig) {
        switch (type.getFileType()) {
            case CLASS:
                return getClassFileTemplate(type, crudConfig);
            case INTERFACE:
            case XML:
        }
        return null;
    }

    private static Template getClassFileTemplate(ModuleType type, CrudConfig crudConfig) {
        ClassFileTemplate classFileTemplate = new ClassFileTemplate(type,crudConfig);
        return classFileTemplate;
    }
}
