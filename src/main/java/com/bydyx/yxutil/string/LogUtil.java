package com.bydyx.yxutil.string;

/**
 * @author qiang.feng
 * @date 2019/11/4 13:37
 */
public class LogUtil {

    private static StringBuilder sb = new StringBuilder();

    public static String getCurrentLocator() {
        sb.setLength(0);
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        StackTraceElement tmp = trace[2];
        return sb.append(tmp.getMethodName())
                 .append("(")
                 .append(tmp.getFileName())
                 .append(":")
                 .append(tmp.getLineNumber())
                 .append(")").toString();
    }
}