package com.bydyx.file;

import com.bydyx.exception.UtilException;
import com.bydyx.string.FilePathUtil;
import com.bydyx.string.PrintUtil;
import lombok.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @date 2020/8/11 10:18
 */
public class ScanPackageUtil {
    private String basePackage;
    private ClassLoader cl;

    @Getter
    List<String> fullyQualifiedClassNameList = new LinkedList<>();

    public ScanPackageUtil(String basePackage) {
        this.basePackage = basePackage;
        this.cl = getClass().getClassLoader();
        fullyQualifiedClassNameScan();
    }

    /**
     * 包下所有类限定名列表初始化
     *
     * @return A list of fully qualified names.
     * @throws IOException
     */
    private void fullyQualifiedClassNameScan() {
        PrintUtil.print("开始扫描包{}下的所有类", basePackage);
        doScan(basePackage);
        PrintUtil.printList("找到", fullyQualifiedClassNameList);
    }

    /**
     * Actually perform the scanning procedure.
     *
     * @throws IOException
     */
    private void doScan(String basePackage) {
        getClassName(basePackage)
        .forEach(name -> {
            if (FilePathUtil.isClassFile(name)) {
                String className = toFullyQualifiedName(name, basePackage);
                fullyQualifiedClassNameList.add(className);
            } else {
                doScan(basePackage + "." + name);
            }
        });
    }

    private List<String> getClassName(String basePackage) {
        String splashPath = FilePathUtil.dotToSplash(basePackage);
        URL url = cl.getResource(splashPath);
        String filePath = FilePathUtil.getRootPath(url);

        if (FilePathUtil.isJarFile(filePath)) {
            return readFromJarFile(filePath, splashPath);
        }
        return readFromDirectory(filePath);
    }

    private List<String> readFromDirectory(String path) {
        File file = new File(path);
        String[] names = file.list();
        return null == names ? null : Arrays.asList(names);
    }

    private String toFullyQualifiedName(String shortName, String basePackage) {
        return new StringBuilder(basePackage).append('.')
                                             .append(FilePathUtil.trimExtension(shortName))
                                             .toString();
    }

    private List<String> readFromJarFile(String jarPath, String splashedPackageName) {
        try (FileInputStream fileInputStream = new FileInputStream(jarPath);
             JarInputStream jarInputStream = new JarInputStream(fileInputStream)
        ) {
            JarEntry entry;
            List<String> nameList = new ArrayList<>();
            while ((entry = jarInputStream.getNextJarEntry()) != null) {
                String name = entry.getName();
                if (name.startsWith(splashedPackageName)) {
                    if (FilePathUtil.isClassFile(name)) {
                        nameList.add(name);
                    }
                }
            }
            return nameList;
        } catch (IOException e) {
            throw new UtilException("读取jar失败:" + e.getMessage(), e);
        }
    }
}