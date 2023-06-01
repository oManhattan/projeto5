package com.timesete.projeto5.business.util;

import java.util.regex.Pattern;

public class Utilities {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    /**
     * Validates email address
     * 
     * @param email email to validate
     * @return true if email is valid
     */
    public static boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    
    /**
     * Password must have at least 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character
     * 
     * @param password password to validate
     * @return true if password is valid
     */
    public static boolean validatePassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * @param <T> type of the value
     * @param value value to check
     * @param defaultValue default value
     * @return value if it is not null, otherwise returns the default value
     */
    public <T> T getOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    /**
     * @param value value to check
     * @param defaultValue default value
     * @return value if it is not null or blank, otherwise returns the default value
     */
    public String getOrDefault(String value, String defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value.isBlank() ? defaultValue : value;
    }
}
