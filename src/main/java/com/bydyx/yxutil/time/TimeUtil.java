package com.bydyx.yxutil.time;

import com.bydyx.yxutil.string.StringUtil;
import com.bydyx.yxutil.time.entity.TimeFormat;
import com.bydyx.yxutil.time.exception.TimeStampFormatException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bydyx
 * @date 2019/10/18 16:47
 */
public class TimeUtil {

    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * @DESCRIPTION: 返回当前时间戳
     * @DATE: 2018/7/31 23:13
     */
    public static String getTimeStamp() {
        return Long.toString(System.currentTimeMillis());
    }

    /**
     * @DESCRIPTION: 返回当前时间戳
     * @DATE: 2018/7/31 23:13
     */
    public static String getTimeStamp(int length) {
        return Long.toString(System.currentTimeMillis()).substring(0, length);
    }

    /**
     * 返回当前时间
     */
    public static String getDateTime(TimeFormat format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getFormat());
        return sdf.format(new Date());
    }

    /**
     * 时间戳转换成时间格式的str
     */
    public static String timeStampTransformToDate(String timeStamp) throws TimeStampFormatException {
        int length1 = 13;
        int length2 = 10;
        if (null == timeStamp || timeStamp.isEmpty() || !StringUtil.isInteger(timeStamp) || timeStamp.length() > length1 || timeStamp.length() < length2) {
            throw new TimeStampFormatException();
        }
        if (timeStamp.length() == length2) {
            timeStamp = timeStamp + "000";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Long.valueOf(timeStamp));
        return sdf.format(date);
    }
}
