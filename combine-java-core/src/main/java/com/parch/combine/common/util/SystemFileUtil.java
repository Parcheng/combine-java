package com.parch.combine.common.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * 系统文件工具类
 */
public class SystemFileUtil {

    /**
     * 读取系统文件
     *
     * @param path 路径
     * @return 内容
     */
    public static String read(String path) {
        if (CheckEmptyUtil.isEmpty(path)) {
            return null;
        }

        if (path.charAt(0) == '/') {
            path = path.substring(1);
        }

        InputStream stream = ClassLoader.getSystemResourceAsStream(path);
        if (stream == null) {
            return null;
        }

        // 读取文件内容
        byte[] data;
        try {
            data = stream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new String(data);
    }
}
