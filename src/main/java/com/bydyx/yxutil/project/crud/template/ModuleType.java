package com.bydyx.yxutil.project.crud.template;


/**
 * @author qiang.feng
 * @date 2020/1/9 11:18
 */
public enum ModuleType {
    controller(FileType.CLASS, "Controller"),
    serviceImpl(FileType.CLASS, "Service"),
    dao(FileType.CLASS, "Dao"),
    mapper(FileType.INTERFACE, "Mapper"),
    mapperXml(FileType.XML, "Mapper"),
    entity(FileType.CLASS, ""),
    ;
    FileType fileType;
    String moduleTypeName;

    ModuleType(FileType fileType, String moduleTypeName) {
        this.fileType = fileType;
        this.moduleTypeName = moduleTypeName;
    }

    public String getSuffix() {
        return moduleTypeName + fileType.getSuffix();
    }

}