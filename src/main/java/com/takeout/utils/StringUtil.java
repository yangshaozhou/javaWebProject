package com.takeout.utils;

public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean hasEmpty(String... str) {
        if (str == null) return true;
        for (String s : str)
            if (isEmpty(s)) return true;
        return false;
    }

    public static boolean isNotNull(String value) {
        if(value == null || "".equals(value.trim()) || "null".equalsIgnoreCase(value)) {
            return false;
        }
        return true;
    }
}
