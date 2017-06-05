package com.yuplus.publiccloud.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zlzsa on 2017/5/30.
 */

public class DateUtils {

    /**
     * @param jsonDate
     * @param fmt
     * @return yyyy-MM-dd'T'HH:mm:ss.SSSZ
     */
    public static String timeFormat(String jsonDate, String fmt) {
        int year = Integer.parseInt(jsonDate.substring(0, 4));
        int month = Integer.parseInt(jsonDate.substring(5, 7));
        int day = Integer.parseInt(jsonDate.substring(8, 10));
        int hrs = Integer.parseInt(jsonDate.substring(11, 13));
        int min = Integer.parseInt(jsonDate.substring(14, 16));
        int sec = Integer.parseInt(jsonDate.substring(17, 19));

        Date date = new Date(year - 1900, month, day, hrs, min, sec);
        DateFormat format = new SimpleDateFormat(fmt);
        String result = format.format(date);

        return result;
    }
}
