package com.bydyx.yxutil.project.crud.template;

public enum FileType {
    CLASS(".java"),
    INTERFACE(".java"),
    XML(".xml"),
    ;
    String suffix;

    FileType(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
