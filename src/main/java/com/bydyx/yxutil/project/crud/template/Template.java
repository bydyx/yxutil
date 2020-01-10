package com.bydyx.yxutil.project.crud.template;

import java.util.List;

public interface Template {

    CrudConfig getCrudConfig();

    /**
     * 返回模版的每行字符串List
     */
    List<String> getTemplateLineList();

    /**
     * 返回模板实例的文件名称
     */
    String getFileName();

    /**
     * 返回模板实例的生成位置
     */
    String getPath();

}