package com.bydyx.yxutil.project.crud;


import com.bydyx.yxutil.database.jdbc.DataBaseConfig;
import com.bydyx.yxutil.file.FileUtil;
import com.bydyx.yxutil.project.crud.template.CrudConfig;
import com.bydyx.yxutil.project.crud.template.ModuleType;
import com.bydyx.yxutil.project.crud.template.Template;
import com.bydyx.yxutil.project.crud.template.TemplateFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * SSM自动生成CRUD模板工具
 *
 * @author qiang.feng
 * @date 2020/1/8 11:04
 */
public class AutomationCrudUtil {

    public static void main(String[] args) {
        AutomationCrudUtil automationCrudUtil = new AutomationCrudUtil();
        List<ModuleType> moduleTypeList = automationCrudUtil.getModuleTypeList();
        CrudConfig crudConfig = automationCrudUtil.getCrudConfig();
        automationCrudUtil.autoCreateCrud(crudConfig, moduleTypeList);
    }

    private CrudConfig getCrudConfig() {
        DataBaseConfig dataBaseConfig = DataBaseConfig.pgSqlConfig("sdm", "postgres", "hnhsoft2828", "192.168.100.181");
        dataBaseConfig.setSchema("alc_tenant_hvactest");

        CrudConfig crudConfig = new CrudConfig(dataBaseConfig, "com.test");
        crudConfig.setProjectUrl("C:\\Users\\450s\\Desktop\\test");
        return crudConfig;
    }

    private List<ModuleType> getModuleTypeList() {
        List<ModuleType> moduleTypeList = new ArrayList<>();
        moduleTypeList.add(ModuleType.controller);
        return moduleTypeList;
    }

    public void autoCreateCrud(CrudConfig crudConfig, List<ModuleType> moduleTypeList) {
        moduleTypeList.forEach(moduleType -> createFile(moduleType,crudConfig));
    }

    private void createFile(ModuleType moduleType, CrudConfig crudConfig) {
        Template template = TemplateFactory.getFileTemplate(moduleType, crudConfig);
        template.getPath();
        template.getFileName();
        template.getTemplateLineList();
        FileUtil.createAndWriteFile();
    }
}