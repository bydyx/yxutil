package com.bydyx.yxutil.file;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author qiang.feng
 * @date 2020/1/9 9:55
 */
@Slf4j
public class FileUtil {

    /**
     * 获取项目根目录
     */
    public static String getProjectPath() {
        File file = new File("");
        return file.getAbsolutePath();
    }

    public static void createAndWriteFile(String path, String fileName, List<String> lines) {
        File file = new File(path + File.separator + fileName);
        createFile(file);
        writeFile(file, lines);
    }

    public static void createFile(String path, String fileName) {
        File file = new File(path + File.separator + fileName);
        createFile(file);
    }

    public static void createFile(File file) {
        try {
            checkFilePath(file);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                log.warn("路径:{} 下已经存在该文件:{} ", file.getPath(), file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkFilePath(File file) {
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
    }

    public static void writeFile(File file, List<String> lines) {
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            for (String line : lines) {
                byte[] bytes = line.getBytes();
                bos.write(bytes);
                bos.write("\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String splicingPath(String... strS) {
        return Arrays.asList(strS)
                     .stream()
                     .reduce((s, s2) -> s + File.separator + s2)
                     .orElse("");
    }
}