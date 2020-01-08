package com.bydyx.yxutil.project.crud;

import lombok.Data;

/**
 * SSM自动生成CRUD模板工具
 *
 * @author qiang.feng
 * @date 2020/1/8 11:04
 */
@Data
public class AutomationCrudUtil {
    // 数据库信息
    String dataBaseUrl;
    String tableName;
    String dbUserName;
    String dbPassWord;

    // 项目根目录
    String projectUrl;
    String controllerUrl;
    String serviceUrl;
    String daoUrl;
    String entityUrl;
    String mapperUrl;
    String mapperXmlUrl;
    // {modelName}Controller,{modelName}Dao.....
    String modelName;

    //是否使用lomBook @Data自动生成getter&&setter
    boolean isUseLomBook;

    public void autoCreateCrud() {

        return;
    }

}