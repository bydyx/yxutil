package com.bydyx.yxutil.project.crud.template;

import lombok.Data;

import java.util.List;

/**
 * @author qiang.feng
 * @date 2020/1/9 11:29
 */
@Data
public class MethodTemplate {
    List<String> modifierList;
    String returnType;
    String methodName;
    List<VariableTemplate> variableTemplateList;
    List<String> exceptionList;
}