package com.parch.combine.web.util;

import com.parch.combine.common.util.StringUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件读取工具类
 */
public class ResourceFileUtil {

    /**
     * 读取文件
     *
     * @param path 路径
     * @return 文件内容
     * @throws IOException 异常
     */
    public static String readFile(String path) throws IOException {
        List<String> result = readFileAsList(path);
        return StringUtil.join(result, "");
    }

    /**
     * 读取文件
     *
     * @param path 路径
     * @return 文件内容
     * @throws IOException 异常
     */
    public static List<String> readFileAsList(String path) throws IOException {
        List<String> result = new ArrayList<>();

        Resource resource = new ClassPathResource(path);
        InputStream inputStream = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        while (true) {
            String readStr = bufferedReader.readLine();
            if (readStr == null) {
                break;
            }
            result.add(readStr);
        }

        inputStream.close();
        inputStreamReader.close();
        bufferedReader.close();

        return result;
    }
}
