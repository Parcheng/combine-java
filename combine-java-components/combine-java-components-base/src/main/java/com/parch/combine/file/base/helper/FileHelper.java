package com.parch.combine.file.base.helper;

import java.io.File;

/**
 * 文件处理
 */
public class FileHelper {

    /**
     * 创建目录
     *
     * @param path 路径
     */
    public static void mkdirs(String path) {
        File file = new File(path);
        if (!path.endsWith("/") && !path.endsWith("\\")) {
            file = file.getParentFile();
        }

        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
