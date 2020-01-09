package com.bydyx.yxutil.project.crud.template;

import lombok.Data;

import java.util.List;

/**
 * @author qiang.feng
 * @date 2020/1/9 11:32
 */
@Data
public class VariableTemplate {
    List<String> annotationList;
    List<String> modifierList;
    String variableName;
    String assignmentSentence;
}