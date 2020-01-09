package com.bydyx.yxutil.project.crud.template;

import com.bydyx.yxutil.database.jdbc.DataBaseConfig;
import com.bydyx.yxutil.file.FileUtil;
import com.bydyx.yxutil.string.StringUtil;
import lombok.Data;

import java.io.File;
import java.util.regex.Matcher;

/**
 * @author qiang.feng
 * @date 2020/1/9 10:15
 */
@Data
public class CrudConfig {
    // 数据库信息
    String dataBaseUrl;
    String tableName;
    String dbUserName;
    String dbPassWord;
    DataBaseConfig dataBaseConfig;

    // 项目根目录
    String projectUrl = "C:\\Users\\450s\\Desktop\\test";
    // 必须填写
    String packageUrl;
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

    ModuleType moduleType;

    public String getPath() {
        return getPackagePath();
    }

    public String getFileName() {
        return StringUtil.firstLetterLowerCase(modelName) + moduleType.getSuffix();
    }

    public CrudConfig(DataBaseConfig dataBaseConfig, String packageUrl) {
        this.dataBaseConfig = dataBaseConfig;
        this.packageUrl = packageUrl;
    }

    public String getProjectUrl() {
        if (StringUtil.isBlank(projectUrl)) {
            projectUrl = FileUtil.getProjectPath();
        }
        return projectUrl + File.separator +
        "src" + File.separator +
        "main" + File.separator;
    }

    public String getPackagePath() {
        packageUrl = packageUrl.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
        return getProjectUrl() + "java" + File.separator + packageUrl;
    }

    public String getResourceUrl() {
        return getProjectUrl() + "resource" + File.separator;
    }

    public String getControllerUrl() {
        if (StringUtil.isBlank(controllerUrl)) {
            controllerUrl = "controller";
        }
        return getPackagePath() + File.separator + controllerUrl;
    }

    public String getServiceUrl() {
        if (StringUtil.isBlank(serviceUrl)) {
            serviceUrl = "service";
        }
        return getPackagePath() + File.separator + serviceUrl;
    }

    public String getDaoUrl() {
        if (StringUtil.isBlank(daoUrl)) {
            daoUrl = "dao";
        }
        return getPackagePath() + File.separator + daoUrl;
    }

    public String getMapperUrl() {
        if (StringUtil.isBlank(mapperUrl)) {
            mapperUrl = "dao" + File.separator + "mapper";
        }
        return getPackagePath() + File.separator + mapperUrl;
    }

    public String getEntityUrl() {
        if (StringUtil.isBlank(entityUrl)) {
            entityUrl = "entity";
        }
        return getPackagePath() + File.separator + entityUrl;
    }

    public String getMapperXmlUrl() {
        if (StringUtil.isBlank(mapperXmlUrl)) {
            mapperXmlUrl = "mapper";
        }
        return getResourceUrl() + File.separator + mapperXmlUrl;
    }

    public String getDaoFileName() {
        return StringUtil.firstLetterLowerCase(modelName) + "Dao.java";
    }
}
