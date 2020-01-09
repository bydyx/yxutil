package com.bydyx.yxutil.project.crud.template;

import java.util.List;

public interface Template {

    /**
     * 返回模版的每行的字符串
     */
    List<String> getTemplateLineList();

    CrudConfig getCrudConfig();

    /**
     * 返回模板实例的生成位置
     */
    default String getPath() {
        return getCrudConfig().getPath();
    }

    /**
     * 返回模板实例的文件名称
     */
    default String getFileName(){
        return getCrudConfig().getFileName();
    }

}