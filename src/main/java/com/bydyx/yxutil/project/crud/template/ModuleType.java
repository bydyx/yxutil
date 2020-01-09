package com.bydyx.yxutil.project.crud.template;


/**
 * @author qiang.feng
 * @date 2020/1/9 11:18
 */
public enum ModuleType{
    controller(7, FileType.CLASS, "Controller"),
    serviceImpl(5, FileType.CLASS, "Service"),
    dao(4, FileType.CLASS, "Dao"),
    mapper(3, FileType.INTERFACE, "Mapper"),
    mapperXml(2, FileType.XML, "Mapper"),
    entity(1, FileType.CLASS, ""),
    ;
    Integer index;
    FileType fileType;
    String moduleTypeName;

    ModuleType(Integer index, FileType fileType, String moduleTypeName) {
        this.index = index;
        this.fileType = fileType;
        this.moduleTypeName = moduleTypeName;
    }



    public String getSuffix() {
        return moduleTypeName + fileType.getSuffix();
    }

    public FileType getFileType() {
        return fileType;
    }

    public String getModuleTypeName() {
        return moduleTypeName;
    }
}