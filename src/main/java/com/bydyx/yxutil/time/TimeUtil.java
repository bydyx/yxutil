package com.bydyx.yxutil.time;

import com.bydyx.yxutil.Constant;
import com.bydyx.yxutil.string.StringUtil;
import com.bydyx.yxutil.time.entity.TimeFormat;
import com.bydyx.yxutil.time.entity.TimeUnit;
import com.bydyx.yxutil.time.exception.TimeStampFormatException;
import com.google.common.math.IntMath;

import java.text.ParseException;
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

    public static String getCurrentDateStr(){
        return dateTimeFormat(new Date(), TimeFormat.HyyyyMMddHHmmss);
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
        int offset = Constant.TimeStampMaxLength - length;
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
        if (length < Constant.TimeStampMinLength || length > Constant.TimeStampMaxLength) {
            throw new TimeStampFormatException("时间戳位数不符合要求");
        }
    }

    public static Date add(TimeUnit timeUnit, int num) {
        return add(new Date(), timeUnit, num);
    }

    //时间操作
    public static Date add(Date date, TimeUnit timeUnit, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(timeUnit.getValue(), num);
        return calendar.getTime();
    }

    /**
     * 返回秒数
     *
     * @param num      数量
     * @param timeUnit 单位,暂时只有时分秒
     * @return
     */
    public static int getSecond(int num, TimeUnit timeUnit) {
        // 时间级别计算,为秒时level = 0;
        int level = 13 - timeUnit.getValue();
        int unitSeconds = IntMath.pow(60, level);
        return unitSeconds * num;
    }

    public static Date parseDateStr(String dateStr){
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式错误!");
        }
    }
}