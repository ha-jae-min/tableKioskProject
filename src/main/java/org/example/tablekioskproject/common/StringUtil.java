package org.example.tablekioskproject.common;

public class StringUtil {

    public static int getInt(String str, int def) {

        if (str == null || str.length() == 0){
            return def;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return def;
        }
    }
}
