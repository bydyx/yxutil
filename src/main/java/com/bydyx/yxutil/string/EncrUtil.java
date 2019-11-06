package com.bydyx.yxutil.string;

import java.security.MessageDigest;

/**
 * @author qiang.feng
 * @date 2019/11/6 11:02
 */
public class EncrUtil {

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String md5(String origin) {
        try {
            byte[] bytes = origin.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(bytes);
            return byteArrayToHexString(digest).toUpperCase();
        } catch (Exception exception) {
            throw new RuntimeException("MD5Encode异常:" + exception.getMessage());
        }
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
