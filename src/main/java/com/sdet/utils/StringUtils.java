package com.sdet.utils;

import org.apache.log4j.Logger;

/**
 * String utility methods
 * Multiple approaches for common string operations
 */
public class StringUtils {
    private static Logger logger = Logger.getLogger(StringUtils.class);

    /**
     * Approach 1: Check if string is empty or null
     */
    public static boolean isEmpty(String str) {
        boolean empty = str == null || str.trim().isEmpty();
        logger.debug("String empty check: " + empty);
        return empty;
    }

    /**
     * Approach 2: Check if string is not empty
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Approach 3: Trim whitespace
     */
    public static String trim(String str) {
        if (isEmpty(str)) return "";
        return str.trim();
    }

    /**
     * Approach 4: Convert to lowercase
     */
    public static String toLowerCase(String str) {
        if (isEmpty(str)) return "";
        return str.toLowerCase();
    }

    /**
     * Approach 5: Convert to uppercase
     */
    public static String toUpperCase(String str) {
        if (isEmpty(str)) return "";
        return str.toUpperCase();
    }

    /**
     * Approach 6: Check if string contains substring
     */
    public static boolean contains(String str, String substring) {
        if (isEmpty(str) || isEmpty(substring)) return false;
        return str.contains(substring);
    }

    /**
     * Approach 7: Replace all occurrences
     */
    public static String replaceAll(String str, String find, String replace) {
        if (isEmpty(str)) return "";
        return str.replaceAll(find, replace);
    }

    /**
     * Approach 8: Split string by delimiter
     */
    public static String[] split(String str, String delimiter) {
        if (isEmpty(str)) return new String[0];
        return str.split(delimiter);
    }

    /**
     * Approach 9: Join array elements with delimiter
     */
    public static String join(String[] array, String delimiter) {
        if (array == null || array.length == 0) return "";
        return String.join(delimiter, array);
    }

    /**
     * Approach 10: Check if string matches regex pattern
     */
    public static boolean matches(String str, String pattern) {
        if (isEmpty(str) || isEmpty(pattern)) return false;
        return str.matches(pattern);
    }

    /**
     * Approach 11: Extract numbers from string
     */
    public static String extractNumbers(String str) {
        if (isEmpty(str)) return "";
        return str.replaceAll("[^0-9]", "");
    }

    /**
     * Approach 12: Concatenate strings safely
     */
    public static String concatenate(String... strings) {
        StringBuilder result = new StringBuilder();
        for (String s : strings) {
            if (isNotEmpty(s)) {
                result.append(s);
            }
        }
        return result.toString();
    }

    /**
     * Approach 13: Check if string starts with prefix
     */
    public static boolean startsWith(String str, String prefix) {
        if (isEmpty(str) || isEmpty(prefix)) return false;
        return str.startsWith(prefix);
    }

    /**
     * Approach 14: Check if string ends with suffix
     */
    public static boolean endsWith(String str, String suffix) {
        if (isEmpty(str) || isEmpty(suffix)) return false;
        return str.endsWith(suffix);
    }

    /**
     * Approach 15: Generate random string of specified length
     */
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt((int) (Math.random() * characters.length())));
        }
        logger.info("Random string generated: " + result.toString());
        return result.toString();
    }

    /**
     * Usage examples:
     *
     * // Approach 1: Check if empty
     * boolean empty = StringUtils.isEmpty(null);  // true
     * boolean empty2 = StringUtils.isEmpty("  ");  // true
     *
     * // Approach 2: Check if not empty
     * boolean notEmpty = StringUtils.isNotEmpty("text");  // true
     *
     * // Approach 3: Trim
     * String trimmed = StringUtils.trim("  hello  ");  // "hello"
     *
     * // Approach 4: Lowercase
     * String lower = StringUtils.toLowerCase("HELLO");  // "hello"
     *
     * // Approach 5: Uppercase
     * String upper = StringUtils.toUpperCase("hello");  // "HELLO"
     *
     * // Approach 6: Contains
     * boolean contains = StringUtils.contains("hello world", "world");  // true
     *
     * // Approach 7: Replace
     * String replaced = StringUtils.replaceAll("hello world", "world", "java");
     * // Result: "hello java"
     *
     * // Approach 8: Split
     * String[] parts = StringUtils.split("a,b,c", ",");
     * // Result: ["a", "b", "c"]
     *
     * // Approach 9: Join
     * String joined = StringUtils.join(new String[]{"a", "b", "c"}, "-");
     * // Result: "a-b-c"
     *
     * // Approach 10: Regex match
     * boolean isEmail = StringUtils.matches("user@test.com", ".*@.*\\..*");
     *
     * // Approach 11: Extract numbers
     * String numbers = StringUtils.extractNumbers("Price: $123.45");
     * // Result: "12345"
     *
     * // Approach 12: Concatenate
     * String concat = StringUtils.concatenate("Hello", " ", "World");
     * // Result: "Hello World"
     *
     * // Approach 13: Starts with
     * boolean starts = StringUtils.startsWith("hello.txt", "hello");  // true
     *
     * // Approach 14: Ends with
     * boolean ends = StringUtils.endsWith("test.csv", ".csv");  // true
     *
     * // Approach 15: Random string
     * String random = StringUtils.generateRandomString(10);
     * // Result: "aBcD1eF2gH"
     */
}
