package com.bydyx.yxutil.file;

import java.io.*;
import java.util.List;

/**
 * @author qiang.feng
 * @date 2020/1/9 9:55
 */
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
                bos.write("\\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}