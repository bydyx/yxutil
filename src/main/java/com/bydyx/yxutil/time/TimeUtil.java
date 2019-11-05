package com.bydyx.yxutil.time;

import com.bydyx.yxutil.Dictionaries;
import com.bydyx.yxutil.IfUtil;
import com.bydyx.yxutil.param.ParamUtil;
import com.bydyx.yxutil.param.exception.ParamWrongException;
import com.bydyx.yxutil.string.StringUtil;
import com.bydyx.yxutil.time.entity.TimeFormat;
import com.bydyx.yxutil.time.entity.TimeType;
import com.bydyx.yxutil.time.exception.TimeStampFormatException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author bydyx
 * @date 2019/10/18 16:47
 */
public class TimeUtil {

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date parseDate(String timeStamp) {
        Long timeLong = Long.valueOf(timeStamp);
        Date date = new Date(timeLong);
        return date;
    }

    /**
     * 返回当前时间戳
     */
    public static String getTimeStamp() {
        return Long.toString(System.currentTimeMillis());
    }

    public static String getTimeStamp(int length) {
        return Long.toString(System.currentTimeMillis()).substring(0, length);
    }

    public static String getDateTime(TimeFormat format) {
        return dateTimeFormat(new Date(), format);
    }

    /**
     * 返回当前时间
     */
    public static String dateTimeFormat(Date date, TimeFormat format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getFormat());
        String result = sdf.format(date);
        return result;
    }

    /**
     * 时间戳转换成时间格式的str
     */
    public static String timeStampTransformToDate(String timeStamp) throws TimeStampFormatException {
        timeStamp = TimeUtil.completionTimeStamp(timeStamp);
        Date date = parseDate(timeStamp);
        return dateTimeFormat(date, TimeFormat.HyyyyMMddHHmmss);
    }

    // 补全13位时间戳,在最后拼接0
    public static String completionTimeStamp(String timeStamp) {
        checkTimeStamp(timeStamp);
        int length = timeStamp.length();
        int offset = Dictionaries.TimeStampMaxLength - length;
        for (int i = 0; i < offset; i++) {
            timeStamp = timeStamp + "0";
        }
        return timeStamp;
    }

    public static void checkTimeStamp(String timeStamp) {
        if (!StringUtil.isInteger(timeStamp) || StringUtil.isBlank(timeStamp)) {
            throw new TimeStampFormatException();
        }
        int length = timeStamp.length();
        if (length < Dictionaries.TimeStampMinLength || length > Dictionaries.TimeStampMaxLength) {
            throw new TimeStampFormatException("时间戳位数不符合要求");
        }
    }

    public static Date add(TimeType timeType, int num) {
        Date date = new Date();
        add(date, timeType, num);
        return date;
    }

    //时间操作,type:Calendar.
    public static void add(Date date, TimeType timeType, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(timeType.getValue(), num);
    }

}