package com.globalcode.ges.model.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date utility helper class
 */
public class DateHelper {
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Format date to string
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return DATE_FORMAT.format(date);
    }
    
    /**
     * Format datetime to string
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        }
        return DATETIME_FORMAT.format(date);
    }
    
    /**
     * Add days to date
     */
    public static Date addDays(Date date, int days) {
        if (date == null) {
            return null;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
    
    /**
     * Add hours to date
     */
    public static Date addHours(Date date, int hours) {
        if (date == null) {
            return null;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
    
    /**
     * Check if date is in the past
     */
    public static boolean isPast(Date date) {
        if (date == null) {
            return false;
        }
        return date.before(new Date());
    }
    
    /**
     * Check if date is in the future
     */
    public static boolean isFuture(Date date) {
        if (date == null) {
            return false;
        }
        return date.after(new Date());
    }
    
    /**
     * Get start of day
     */
    public static Date getStartOfDay(Date date) {
        if (date == null) {
            return null;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    /**
     * Get end of day
     */
    public static Date getEndOfDay(Date date) {
        if (date == null) {
            return null;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
