package com.bydyx.yxutil.project.crud.template;

import lombok.Data;

import java.util.List;

/**
 * @author qiang.feng
 * @date 2020/1/9 11:23
 */
@Data
public class ClassFileTemplate implements Template {
    String packageStr;
    ModuleType moduleType;
    List<String> importList;
    List<String> annotationList;
    List<MethodTemplate> methodTemplateList;
    List<VariableTemplate> variableTemplateList;

    CrudConfig crudConfig;

    public ClassFileTemplate(ModuleType moduleType, CrudConfig crudConfig) {
        this.crudConfig = crudConfig;
        this.moduleType = moduleType;
        this.packageStr = crudConfig.getPackagePath();
    }

    @Override
    public CrudConfig getCrudConfig() {
        return crudConfig;
    }

    @Override
    public List<String> getTemplateLineList() {
        return null;
    }
}
