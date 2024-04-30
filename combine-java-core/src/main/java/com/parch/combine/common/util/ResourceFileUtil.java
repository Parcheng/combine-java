package com.parch.combine.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 系统文件工具类
 */
public class ResourceFileUtil {

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

        StringBuilder result = new StringBuilder();
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
             BufferedReader reader = inputStream == null ? null : new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            if (reader == null) {
                PrintUtil.printError("配置文件：" + path + "不存在！");
            } else {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (Exception e) {
            PrintUtil.printError("读取配置文件异常：" + e.getMessage());
            e.printStackTrace();
        }

        return result.toString();
    }
}
