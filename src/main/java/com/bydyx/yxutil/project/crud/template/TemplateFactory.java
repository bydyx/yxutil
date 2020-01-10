package com.bydyx.yxutil.project.crud.template;

/**
 * @author qiang.feng
 * @date 2020/1/9 11:20
 */
public class TemplateFactory {

    public static Template getFileTemplate(LayerType type, CrudConfig crudConfig) {
        switch (type) {
            case entity:
                return getEntityTemplate(type, crudConfig);
        }
        return null;
    }

    private static Template getEntityTemplate(LayerType type, CrudConfig crudConfig) {
        ClassFileTemplate template = getClassFileTemplate(type, crudConfig);
        return template;
    }


    private static ClassFileTemplate getClassFileTemplate(LayerType type, CrudConfig crudConfig) {
        ClassFileTemplate template = new ClassFileTemplate(type, crudConfig);
        return template;
    }
}
