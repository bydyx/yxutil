package com.bydyx.yxutil.project.crud;


import com.bydyx.yxutil.database.jdbc.DataBaseConfig;
import com.bydyx.yxutil.file.FileUtil;
import com.bydyx.yxutil.project.crud.template.CrudConfig;
import com.bydyx.yxutil.project.crud.template.ModuleType;
import com.bydyx.yxutil.project.crud.template.Template;
import com.bydyx.yxutil.project.crud.template.TemplateFactory;


/**
 * SSM自动生成CRUD模板工具
 *
 * @author qiang.feng
 * @date 2020/1/8 11:04
 */
public class AutomationCrudUtil {

    public static void main(String[] args) {
        DataBaseConfig dataBaseConfig = DataBaseConfig.pgSqlConfig("sdm", "postgres", "hnhsoft2828", "192.168.100.181");
        dataBaseConfig.setSchema("alc_tenant_hvactest");
        CrudConfig crudConfig = new CrudConfig(dataBaseConfig, "com.test");
        crudConfig.setProjectUrl("C:\\Users\\450s\\Desktop\\test");

        new AutomationCrudUtil().autoCreateCrud(crudConfig);
    }

    public void autoCreateCrud(CrudConfig crudConfig) {
        createController(crudConfig);
    }

    private void createController(CrudConfig crudConfig) {
        Template template = TemplateFactory.getFileTemplate(ModuleType.controller,crudConfig);
        FileUtil.createAndWriteFile(template.getPath(),template.getFileName(),template.getTemplateLineList());
    }
}