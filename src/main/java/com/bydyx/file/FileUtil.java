package com.bydyx.file;


import com.bydyx.exception.UtilException;

import java.io.*;
import java.util.Arrays;

/**
 * @date 2020/8/11 13:00
 */
public class FileUtil {

    public static String readFileByChars(String fileName) {
        return readFileByChars(new File(fileName));
    }

    public static String readFileByChars(File file) {
        try (
        FileInputStream fileInputStream = new FileInputStream(file);
        Reader reader = new InputStreamReader(fileInputStream)
        ) {
            int charRead;
            char[] tempChars = new char[1024];
            StringBuilder stringBuilder = new StringBuilder();
            while ((charRead = reader.read(tempChars)) != -1) {
                if (charRead != tempChars.length) {
                    char[] chars = Arrays.copyOf(tempChars, charRead);
                    stringBuilder.append(chars);
                    continue;
                }
                stringBuilder.append(tempChars);
            }
            return stringBuilder.toString();
        } catch (Exception e1) {
            throw new UtilException(e1);
        }
    }
}