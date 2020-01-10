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
    DataBaseConfig dataBaseConfig;

    // 项目根目录
    String projectPath;
    String packagePath;
    // 必须填写
    String packageStr;
    String controllerUrl;
    String serviceUrl;
    String daoUrl;
    String entityUrl;
    String mapperUrl;
    String mapperXmlUrl;
    // {modelName}Controller,{modelName}Dao.....
    String modelName;

    //是否使用lomBook @Data自动生成getter&&setter
    boolean useLomBook = true;

    private CrudConfig(DataBaseConfig dataBaseConfig, String packageStr) {
        this.dataBaseConfig = dataBaseConfig;
        this.packageStr = packageStr;
    }

    public static CrudConfig createConfig(DataBaseConfig dataBaseConfig, String packageStr) {
        CrudConfig crudConfig = new CrudConfig(dataBaseConfig, packageStr);
        return crudConfig;
    }

    public static CrudConfig createConfig(DataBaseConfig dataBaseConfig, String packageStr, String projectPath) {
        CrudConfig crudConfig = new CrudConfig(dataBaseConfig, packageStr);
        crudConfig.setProjectPath(projectPath);
        crudConfig.projectPathInit();
        return crudConfig;
    }

    public void projectPathInit() {
        if (StringUtil.isBlank(projectPath)) {
            projectPath = FileUtil.getProjectPath();
        }
        projectPath = FileUtil.splicingPath(projectPath, "src", "main");
        packagePath = FileUtil.splicingPath(projectPath, "java", packageToPath(packageStr));
    }

    public String getResourcePath() {
        return FileUtil.splicingPath(projectPath, "resource");
    }

    public static String packageToPath(String packageStr) {
        String separator = Matcher.quoteReplacement(File.separator);
        return packageStr.replaceAll("\\.", separator);
    }
}
