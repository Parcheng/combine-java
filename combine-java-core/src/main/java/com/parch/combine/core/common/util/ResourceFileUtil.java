package com.parch.combine.core.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 系统文件工具类
 */
public class ResourceFileUtil {

    private ResourceFileUtil() {}

    @Deprecated
    public static URL getURL(String path) {
        return getURL(path, ClassLoader.getSystemClassLoader());
    }

    public static URL getURL(String path, ClassLoader classLoader) {
        if (path.charAt(0) == '/') {
            path = path.substring(1);
        }

        return classLoader.getResource(path);
    }

    /**
     * 读取系统文件
     *
     * @param path 路径
     * @return 内容
     */
    @Deprecated
    public static String read(String path) {
        return read(path, ClassLoader.getSystemClassLoader());
    }

    /**
     * 读取系统文件
     *
     * @param path 路径
     * @param classLoader 类加载器
     * @return 内容
     */
    public static String read(String path, ClassLoader classLoader) {
        if (CheckEmptyUtil.isEmpty(path)) {
            return null;
        }

        if (path.charAt(0) == '/') {
            path = path.substring(1);
        }

        StringBuilder result = new StringBuilder();
        try (InputStream inputStream = classLoader.getResourceAsStream(path); // ClassLoader.getSystemResourceAsStream(path)
             BufferedReader reader = inputStream == null ? null : new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            if (reader == null) {
                PrintLogUtil.printError("配置文件：" + path + "不存在！");
            } else {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (Exception e) {
            PrintLogUtil.printError("读取配置文件异常：" + e.getMessage());
            e.printStackTrace();
        }

        return result.toString();
    }
}
