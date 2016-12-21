package cn.edu.nefu.gdms.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by dingyunxiang on 16/12/21.
 */
public abstract class DateUtils  {

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * Private constructor
     */
    private DateUtils() {
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }


    public static String toDateTime(LocalDateTime date) {
        return toDateTime(date, DEFAULT_DATE_TIME_FORMAT);
    }

    public static String toDateTime(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
    }


    public static String toDateText(LocalDate date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
    }


}