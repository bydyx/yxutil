package com.bydyx.yxutil.project.crud.template;

import com.bydyx.yxutil.string.StringUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiang.feng
 * @date 2020/1/9 11:23
 */
@Data
public class ClassFileTemplate implements Template {
    String packageStr;
    String name;
    LayerType layerType;
    List<String> importList = new ArrayList<>();
    List<String> annotationList = new ArrayList<>();
    List<MethodTemplate> methodTemplateList = new ArrayList<>();
    List<VariableTemplate> variableTemplateList = new ArrayList<>();

    List<String> lineList = new ArrayList<>();
    CrudConfig crudConfig;

    public ClassFileTemplate(LayerType layerType, CrudConfig crudConfig) {
        this.layerType = layerType;
        this.crudConfig = crudConfig;
        this.name = layerType.getLayerName(crudConfig);
        this.packageStr = crudConfig.getPackageStr();
        if (crudConfig.useLomBook) {
            addImport("lombok.Data");
        }
    }

    @Override
    public CrudConfig getCrudConfig() {
        return crudConfig;
    }

    @Override
    public String getFileName() {
        String modelName = StringUtil.firstLetterLowerCase(crudConfig.getModelName());
        return modelName + layerType.getSuffix();
    }

    @Override
    public String getPath() {
        return layerType.getPackagePath(crudConfig);
    }

    @Override
    public List<String> getTemplateLineList() {
        lineList.add("package " + layerType.getPackageStr(crudConfig) + ";");
        setImportStr();
        setAnnotation();
        setClassSignature();
        return lineList;
    }

    private void setImportStr() {
        lineList.add("");
        importList.forEach(imStr -> lineList.add("import " + imStr + ";"));
        lineList.add("");
    }

    private void setAnnotation() {
        annotationList.forEach(annotation -> lineList.add("@" + annotation));
    }

    private void setClassSignature() {
        lineList.add("public " + layerType.getFileType() + layerType.getLayerName(crudConfig) + "{");
    }

    public void addImport(String importPackage) {
        importList.add(importPackage);
    }
}
