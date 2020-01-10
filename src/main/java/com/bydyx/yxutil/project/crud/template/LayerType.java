package com.bydyx.yxutil.project.crud.template;


import com.bydyx.yxutil.file.FileUtil;
import com.bydyx.yxutil.string.StringUtil;

import java.io.File;
import java.util.regex.Matcher;

/**
 * @author qiang.feng
 * @date 2020/1/9 11:18
 */
public enum LayerType {
    controller(7, FileType.CLASS, "Controller", FileUtil.splicingPath("controller")),
    service(5, FileType.CLASS, "Service", FileUtil.splicingPath("service")),
    dao(4, FileType.CLASS, "Dao", FileUtil.splicingPath("dao")),
    mapper(3, FileType.INTERFACE, "Mapper", FileUtil.splicingPath("dao", "mapper")),
    mapperXml(2, FileType.XML, "Mapper", FileUtil.splicingPath("mapper")),
    entity(1, FileType.CLASS, "", FileUtil.splicingPath("model", "entity")),
    ;
    Integer index;
    FileType fileType;
    String layerTypeName;
    String packagePath;

    LayerType(Integer index, FileType fileType, String layerTypeName, String packagePath) {
        this.index = index;
        this.fileType = fileType;
        this.packagePath = packagePath;
        this.layerTypeName = layerTypeName;
    }

    public String getSuffix() {
        return layerTypeName + fileType.getSuffix();
    }

    public FileType getFileType() {
        return fileType;
    }

    public Integer getIndex() {
        return index;
    }

    public String getLayerName(CrudConfig crudConfig){
        String s = StringUtil.firstLetterLowerCase(crudConfig.getModelName());
        return s + layerTypeName;
    }

    public String getPackagePath(CrudConfig crudConfig) {
        return FileUtil.splicingPath(crudConfig.getPackagePath(), packagePath);
    }

    public String getPackageStr(CrudConfig crudConfig) {
        return crudConfig.getPackageStr() + "." + packagePath.replaceAll(Matcher.quoteReplacement(File.separator), ".");
    }
}