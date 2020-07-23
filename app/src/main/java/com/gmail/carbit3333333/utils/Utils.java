package com.gmail.carbit3333333.utils;

import java.text.SimpleDateFormat;

/**
 * Created by Руслан on 28.01.2017.
 */

public class Utils {
    public static String getDate(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return dateFormat.format(date);
    }

    public static String getTime(long time) {
        SimpleDateFormat timeformat = new SimpleDateFormat("HH.mm");
        return timeformat.format(time);
    }

    public static String getFullDate(long date) {
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd.MM.yy  HH.mm");
        return fullDateFormat.format(date);
    }
}
