package com.e2on.assignment.util;

public class FileUtils {
    public static String extractExt(String originalImageName) {
        int pos = originalImageName.lastIndexOf(".");
        return "." + originalImageName.substring(pos + 1);
    }

    public static String removeExt(String originalImageName) {
        int pos = originalImageName.lastIndexOf(".");
        return originalImageName.substring(0, pos);
    }
}
