package de.fh.kiel.advancedjava.pojomodel.util;

public class ParseUtil {

    private ParseUtil(){}

    public static String parsePackageName(String completePath) {
        if (completePath.lastIndexOf(".") != -1)
            return completePath.substring(0, completePath.lastIndexOf("."));
        return completePath;
    }

    public static String parseClassName(String completePath) {
        if (completePath.lastIndexOf(".") != -1)
            return completePath.substring(completePath.lastIndexOf(".") + 1);
        return completePath;
    }
    public static String parseCompletePath(String packageName, String className) {
        return packageName + "." + className;
    }

}

