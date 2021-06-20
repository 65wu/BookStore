package com.example.bookstore.util;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String getFormatDate(Date date) {
        if (date == null) date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return formatter.format(date);
    }
}
