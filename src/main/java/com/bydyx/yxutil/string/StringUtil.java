package com.bydyx.yxutil.string;

import com.bydyx.yxutil.Constant;
import com.bydyx.yxutil.function.Excuter;
import com.bydyx.yxutil.math.IntegerUtil;
import com.bydyx.yxutil.time.TimeUtil;
import com.bydyx.yxutil.time.entity.TimeFormat;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * @author bydyx
 * @date 2019/10/18 16:45
 */
public class StringUtil {

    public static String createVerifyCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int random = IntegerUtil.random(0, baseNumLetter.length);
            sb.append(baseNumLetter[random]);
        }
        return sb.toString();
    }

    public static boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 都为空->true
     *
     * @date 2019/12/2 15:26
     * @author qiang.feng
     */
    public static boolean isBlankAnd(String... strs) {
        for (final String str : strs) {
            if (!isBlank(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 有一个为空->true
     *
     * @date 2019/12/2 15:26
     * @author qiang.feng
     */
    public static boolean isBlankOr(String... strs) {
        for (final String str : strs) {
            if (isBlank(str)) {
                return true;
            }
        }
        return false;
    }

    public static String md5(String text) {
        String md5 = DigestUtils.md5Hex(text.getBytes());
        return md5.toUpperCase();
    }

    /**
     * 生成订单编号随机数
     */
    public static String createOrderNo() {
        String time = TimeUtil.getDateTime(TimeFormat.yyyyMMddHHmmss);
        String s = String.valueOf(IntegerUtil.random(10000, 99999));
        return time + s;
    }

    public static boolean isInteger(String str) {
        if (!RegexUtil.isInteger(str)) {
            return false;
        }
        if (str.length() > Constant.IntegerMaxLength) {
            return false;
        }
        return true;
    }

    /**
     * 返回字符串中开始到匹配的最后一个字符(不包含)
     * lastCharToStart("xxx.yyy.zzz",".") => "xxx.yyy"
     */
    public static String lastCharToStart(String str, String ch) {
        int endIndex = str.lastIndexOf(ch);
        if (endIndex == -1) {
            return str;
        }
        return str.substring(0, endIndex);
    }

    /**
     * 返回字符串中匹配的最后一个字符(包含)到结尾
     * lastCharToEnd("xxx.yyy.zzz",".") => ".zzz"
     */
    public static String lastCharToEnd(String str, String ch) {
        int endIndex = str.lastIndexOf(ch);
        if (endIndex == -1) {
            return str;
        }
        return str.substring(endIndex);
    }

    /**
     * 返回字符串中最后一个字符(不包含)到结尾
     * lastCharToEnd("xxx.yyy.zzz",".") => "zzz"
     */
    public static String lastCharToEndNoCh(String str, String ch) {
        int endIndex = str.lastIndexOf(ch);
        if (endIndex == -1) {
            return str;
        }
        endIndex++;
        if (endIndex == str.length()) {
            return "";
        }
        return str.substring(endIndex);
    }

    /**
     * 清除下划线,并把下一个字符转为大写
     *
     * @param str
     * @return
     */
    public static String clearUnderline(String str) {
        int index = str.indexOf("_");
        String ch = str.substring(index + 1, index + 2);
        str = str.replaceAll("_" + ch, ch.toUpperCase());

        if (str.indexOf("_") != -1) {
            return StringUtil.clearUnderline(str);
        }
        return str;
    }

    public static String firstLetterLowerCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    public static String clearUnderlineNoReplace(String str) {
        return str.replaceAll("_", "");
    }

    /**
     * if s1 > s2 1
     * else if s1 < s2 1
     * else 0
     */
    public static int compareString(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return compareStringIter(s1, s2);
        } else {
            return -compareStringIter(s2, s1);
        }
    }

    public static int compareStringIter(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) < s2.charAt(i)) {
                return -1;
            } else if (s1.charAt(i) > s2.charAt(i)) {
                return 1;
            }
        }
        if (s1.length() == s2.length()) {
            return 0;
        }
        return -1;
    }

    public static String randomStr32() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "").toUpperCase();
    }

    private final static char[] baseNumLetter = {
    '1', '2', '3', '4', '5', '6', '7', '8', '9',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
    'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S',
    'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    /**
     * 检查字符串是否含有 params中的字符串
     *
     * @param targetStr 受检字符串
     * @param params    是否含有的字符串
     * @return 只要有一个符合条件就返回true
     */
    public static boolean indexOfOr(String targetStr, String... params) {
        for (final String param : params) {
            if (targetStr.indexOf(param) != -1) {
                return true;
            }
        }
        return false;
    }
}