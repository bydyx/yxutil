package com.bydyx.yxutil.project.crud.template;

/**
 * @author qiang.feng
 * @date 2020/1/9 11:20
 */
public class TemplateFactory {

    public static Template getFileTemplate(ModuleType type, CrudConfig crudConfig) {
        switch (type) {
            case controller:
                return getControllerTemplate(crudConfig);
        }
        return null;
    }

    private static Template getControllerTemplate(CrudConfig crudConfig) {
        ClassFileTemplate classFileTemplate = new ClassFileTemplate();
        return classFileTemplate;
    }
}
