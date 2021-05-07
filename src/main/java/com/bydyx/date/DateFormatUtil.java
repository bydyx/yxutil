package com.bydyx.date;

import com.bydyx.date.model.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author q.f
 * @date 2020/8/3 11:28
 */
public class DateFormatUtil {

    public static String getDateTime(DateFormat format) {
        return dateTimeFormat(new Date(), format);
    }

    public static String dateTimeFormat(Date date, DateFormat format) {
        String formatStr = format.getFormat();
        return new SimpleDateFormat(formatStr).format(date);
    }

}