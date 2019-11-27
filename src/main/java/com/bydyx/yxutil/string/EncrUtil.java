package com.bydyx.yxutil.string;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具
 *
 * @author bydyx
 * @date 2019/11/6 11:02
 */
public class EncrUtil {

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private static final String ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/ECB/PKCS5PADDING";


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

    // AES加密
    public static String encryptData(String data, String aesKey) {
        try {
            Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, aesKey);
            byte[] bytes = data.getBytes();
            byte[] finalBytes = cipher.doFinal(bytes);
            return new BASE64Encoder().encode(finalBytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    //AES解密
    public static String decryptData(String data, String aesKey) {
        try {
            Cipher cipher = getCipher(Cipher.DECRYPT_MODE, aesKey);
            byte[] bytes = new BASE64Decoder().decodeBuffer(data);
            byte[] deBytes = cipher.doFinal(bytes);
            return new String(deBytes);
        } catch (IOException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Cipher getCipher(int cipherType, String aesKey) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
            cipher.init(cipherType, secretKeySpec);
            return cipher;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
