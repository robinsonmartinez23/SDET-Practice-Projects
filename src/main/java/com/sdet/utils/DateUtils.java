package com.sdet.utils;

import org.apache.log4j.Logger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Date/Time utility methods
 * Multiple approaches for common date operations
 */
public class DateUtils {
    private static Logger logger = Logger.getLogger(DateUtils.class);

    /**
     * Approach 1: Get current date in yyyy-MM-dd format
     */
    public static String getCurrentDate() {
        String currentDate = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        logger.info("Current date: " + currentDate);
        return currentDate;
    }

    /**
     * Approach 2: Get current date/time with custom format
     */
    public static String getCurrentDateTime(String format) {
        String currentDateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(format));
        logger.info("Current date/time (" + format + "): " + currentDateTime);
        return currentDateTime;
    }

    /**
     * Approach 3: Get current timestamp in milliseconds
     * Useful for unique IDs, performance testing
     */
    public static long getCurrentTimeMillis() {
        long timestamp = System.currentTimeMillis();
        logger.info("Current timestamp (ms): " + timestamp);
        return timestamp;
    }

    /**
     * Approach 4: Format date with custom pattern
     */
    public static String formatDate(LocalDate date, String pattern) {
        String formatted = date.format(DateTimeFormatter.ofPattern(pattern));
        logger.info("Formatted date: " + formatted);
        return formatted;
    }

    /**
     * Approach 5: Add days to current date
     * Useful for date calculations
     */
    public static String addDaysToCurrentDate(int days) {
        LocalDate futureDate = LocalDate.now().plusDays(days);
        String result = futureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        logger.info("Date after adding " + days + " days: " + result);
        return result;
    }

    /**
     * Approach 6: Subtract days from current date
     */
    public static String subtractDaysFromCurrentDate(int days) {
        LocalDate pastDate = LocalDate.now().minusDays(days);
        String result = pastDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        logger.info("Date after subtracting " + days + " days: " + result);
        return result;
    }

    /**
     * Approach 7: Calculate days between two dates
     */
    public static long daysBetween(LocalDate date1, LocalDate date2) {
        long days = ChronoUnit.DAYS.between(date1, date2);
        logger.info("Days between dates: " + days);
        return days;
    }

    /**
     * Approach 8: Parse date string to LocalDate
     */
    public static LocalDate parseDate(String dateString, String pattern) {
        try {
            LocalDate date = LocalDate.parse(dateString,
                    DateTimeFormatter.ofPattern(pattern));
            logger.info("Date parsed: " + date);
            return date;
        } catch (Exception e) {
            logger.error("Error parsing date: " + e.getMessage());
            return null;
        }
    }

    /**
     * Approach 9: Check if date is today
     */
    public static boolean isToday(LocalDate date) {
        boolean isToday = date.equals(LocalDate.now());
        logger.info("Is today: " + isToday);
        return isToday;
    }

    /**
     * Approach 10: Check if date is in future
     */
    public static boolean isFutureDate(LocalDate date) {
        boolean isFuture = date.isAfter(LocalDate.now());
        logger.info("Is future date: " + isFuture);
        return isFuture;
    }

    /**
     * Approach 11: Get day of week
     * Returns: MONDAY, TUESDAY, WEDNESDAY, etc.
     */
    public static String getDayOfWeek(LocalDate date) {
        String dayOfWeek = date.getDayOfWeek().toString();
        logger.info("Day of week: " + dayOfWeek);
        return dayOfWeek;
    }

    /**
     * Approach 12: Convert old Date to LocalDate (Java 8+)
     */
    public static LocalDate convertToLocalDate(Date date) {
        LocalDate localDate = date.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();
        logger.info("Converted to LocalDate: " + localDate);
        return localDate;
    }

    /**
     * Usage examples:
     *
     * // Approach 1: Current date
     * String today = DateUtils.getCurrentDate();
     * // Result: "2026-05-17"
     *
     * // Approach 2: Custom format
     * String dateTime = DateUtils.getCurrentDateTime("dd-MM-yyyy HH:mm:ss");
     * // Result: "17-05-2026 04:35:22"
     *
     * // Approach 3: Timestamp
     * long ts = DateUtils.getCurrentTimeMillis();
     * // Result: 1715924122000
     *
     * // Approach 4: Format custom date
     * LocalDate date = LocalDate.of(2026, 5, 17);
     * String formatted = DateUtils.formatDate(date, "MMM dd, yyyy");
     * // Result: "May 17, 2026"
     *
     * // Approach 5: Add days
     * String futureDate = DateUtils.addDaysToCurrentDate(30);
     * // Result: "2026-06-16"
     *
     * // Approach 6: Subtract days
     * String pastDate = DateUtils.subtractDaysFromCurrentDate(7);
     * // Result: "2026-05-10"
     *
     * // Approach 7: Days between
     * LocalDate d1 = LocalDate.of(2026, 5, 17);
     * LocalDate d2 = LocalDate.of(2026, 5, 24);
     * long days = DateUtils.daysBetween(d1, d2);
     * // Result: 7
     *
     * // Approach 8: Parse date
     * LocalDate parsed = DateUtils.parseDate("2026-05-17", "yyyy-MM-dd");
     *
     * // Approach 9: Is today?
     * boolean today = DateUtils.isToday(LocalDate.now());
     * // Result: true
     *
     * // Approach 10: Is future date?
     * LocalDate future = LocalDate.now().plusDays(10);
     * boolean isFuture = DateUtils.isFutureDate(future);
     * // Result: true
     */
}
