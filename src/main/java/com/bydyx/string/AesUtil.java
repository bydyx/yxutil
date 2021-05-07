package com.bydyx.string;

import com.bydyx.exception.UtilException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author q.f
 * @date 2020/8/4 16:36
 */
public class AesUtil {

    // 算法名称
    private static final String KEY_ALGORITHM = "AES";

    // 加解密算法/模式/填充方式
    private static final String ALGORITHMSTR = "AES/CBC/PKCS7Padding";

    private static Cipher cipher;

    static {
        Security.addProvider(new BouncyCastleProvider());
        try {
            cipher = Cipher.getInstance(AesUtil.ALGORITHMSTR);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            PrintUtil.print(e);
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param sessionKey    解密密钥
     */
    public static byte[] decrypt(String encryptedData, String sessionKey, String ivStr) {
        try {
            byte[] iv = decode64(ivStr);
            byte[] cipherTextBytes = decode64(encryptedData);

            Key key = createKey(sessionKey);

            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            return cipher.doFinal(cipherTextBytes);
        } catch (Exception e) {
            throw new UtilException(e);
        }
    }

    private static Key createKey(String sessionKey) {
        byte[] aesKey = decode64(sessionKey);
        // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
        aesKey = supplementaryKey(aesKey);
        // 转化成JAVA的密钥格式
        return new SecretKeySpec(aesKey, KEY_ALGORITHM);
    }

    private static byte[] decode64(String str) {
        return Base64.getDecoder()
                     .decode(str);
    }


    private static byte[] supplementaryKey(byte[] keyBytes) {
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        return keyBytes;
    }

    public static String strInit(byte[] result) {
        return new String(result, StandardCharsets.UTF_8);
    }
}