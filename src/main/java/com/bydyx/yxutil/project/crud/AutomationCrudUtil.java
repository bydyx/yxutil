package com.bydyx.yxutil.project.crud;


import com.bydyx.yxutil.database.jdbc.DataBaseConfig;
import com.bydyx.yxutil.file.FileUtil;
import com.bydyx.yxutil.project.crud.template.CrudConfig;
import com.bydyx.yxutil.project.crud.template.LayerType;
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
        List<LayerType> layerTypeList = automationCrudUtil.getModuleTypeList();
        CrudConfig crudConfig = automationCrudUtil.getCrudConfig();
        automationCrudUtil.autoCreateCrud(crudConfig, layerTypeList);
    }

    private CrudConfig getCrudConfig() {
        DataBaseConfig dataBaseConfig = DataBaseConfig.pgSqlConfig("sdm", "postgres", "hnhsoft2828", "192.168.100.181");
        dataBaseConfig.setSchema("alc_tenant_hvactest");
        dataBaseConfig.setTableName("workorder");

        String projectUrl = "C:\\Users\\450s\\Desktop\\test";
        CrudConfig crudConfig = CrudConfig.createConfig(dataBaseConfig, "com.test", projectUrl);
        crudConfig.setModelName("workOrder");
        return crudConfig;
    }

    private List<LayerType> getModuleTypeList() {
        List<LayerType> layerTypeList = new ArrayList<>();
        layerTypeList.add(LayerType.entity);
        return layerTypeList;
    }

    public void autoCreateCrud(CrudConfig crudConfig, List<LayerType> layerTypeList) {
        layerTypeList.forEach(layerType -> createFile(crudConfig, layerType));
    }

    private void createFile(CrudConfig crudConfig, LayerType layerType) {
        Template template = TemplateFactory.getFileTemplate(layerType, crudConfig);
        FileUtil.createAndWriteFile(template);
    }
}