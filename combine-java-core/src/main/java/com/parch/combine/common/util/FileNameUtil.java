package com.parch.combine.common.util;

/**
 * 文件名称工具类
 */
public class FileNameUtil {

    /**
     * 获取文件名称
     *
     * @param path 文件路径
     * @return 后缀
     */
    public static String getName(String path) {
        if (path != null && path.lastIndexOf("/") != -1) {
            String name = path.substring(path.lastIndexOf("/") + 1);
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
     * @param name 文件名
     * @return 后缀
     */
    public static String getPostfix(String name) {
        if (name != null && name.lastIndexOf(".") != -1) {
            String suffix = name.substring(name.lastIndexOf(".") + 1);
            return suffix.toLowerCase();
        }

        return null;
    }
}
