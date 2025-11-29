package com.globalcode.ges.model.util;

/**
 * String utility helper class
 */
public class StringHelper {
    
    /**
     * Check if string is null or empty
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * Check if string is not null and not empty
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * Capitalize first letter of string
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    /**
     * Format name to proper case
     */
    public static String formatName(String name) {
        if (isEmpty(name)) {
            return name;
        }
        
        String[] words = name.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                result.append(" ");
            }
            result.append(capitalize(words[i]));
        }
        
        return result.toString();
    }
    
    /**
     * Clean and format email
     */
    public static String formatEmail(String email) {
        if (isEmpty(email)) {
            return email;
        }
        return email.trim().toLowerCase();
    }
    
    /**
     * Generate course code from name
     */
    public static String generateCourseCode(String courseName) {
        if (isEmpty(courseName)) {
            return "";
        }
        
        String[] words = courseName.trim().toUpperCase().split("\\s+");
        StringBuilder code = new StringBuilder();
        
        for (String word : words) {
            if (word.length() > 0) {
                code.append(word.charAt(0));
            }
        }
        
        return code.toString();
    }
}
