package com.parch.combine.core.common.util;

/**
 * 文件名称工具类
 */
public class FileNameUtil {

    private FileNameUtil() {}

    /**
     * 获取文件名称
     *
     * @param path 文件路径
     * @return 后缀
     */
    public static String getName(String path) {
        if (path == null) {
            return null;
        }

        int lastIndexOf = path.lastIndexOf("/");
        if (lastIndexOf != -1) {
            String name = path.substring(lastIndexOf + 1);
            String postfix = getPostfix(name);
            if (postfix != null) {
                name = name.replace("." + postfix, "");
            }
            return name;
        }

        return null;
    }

    /**
     * 获取文件后缀
     *
     * @param path 文件路径
     * @return 后缀
     */
    public static String getPostfix(String path) {
        if (path == null) {
            return null;
        }

        int lastIndexOf = path.lastIndexOf("/");
        if (lastIndexOf != -1) {
            path = path.substring(lastIndexOf + 1);
        }

        int dotIndexOf = path.lastIndexOf(".");
        if (dotIndexOf == -1) {
            return null;
        }

        String suffix = path.substring(dotIndexOf + 1);
        return suffix.toLowerCase();
    }
}
