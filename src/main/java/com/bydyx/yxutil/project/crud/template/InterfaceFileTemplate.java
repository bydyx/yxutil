package com.bydyx.yxutil.project.crud.template;

import lombok.Data;

import java.util.List;

/**
 * @author qiang.feng
 * @date 2020/1/9 11:38
 */
@Data
public class InterfaceFileTemplate {
    String packageStr;
    List<String> importList;
    List<String> annotationList;
    List<MethodTemplate> methodTemplateList;
}