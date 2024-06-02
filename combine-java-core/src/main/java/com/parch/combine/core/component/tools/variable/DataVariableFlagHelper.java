package com.parch.combine.core.component.tools.variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据变量处理帮助类
 */
public class DataVariableFlagHelper {

    /**
     * 判断是否有解析标识
     *
     * @param data 数据对象
     * @return 是否有解析标识
     */
    @SuppressWarnings("unchecked")
    public static boolean hasParseFlag(Object data) {
        if (data instanceof String) {
            return hasParseFlag(data.toString());
        } else if (data instanceof List) {
            for (Object item : (List<Object>) data) {
                if (hasParseFlag(item)) {
                    return true;
                }
            }
        } else if (data instanceof Map) {
            for (Object item : ((Map<Object, Object>) data).keySet()) {
                if (hasParseFlag(((Map<Object, Object>) data).get(item))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 判断是否有解析标识
     *
     * @param path 路径
     * @return 是否有解析标识
     */
    public static boolean hasParseFlag(String path) {
        return path.length() > 3 && '#' == path.charAt(0) && '{' == path.charAt(1) && '}' == path.charAt(path.length() - 1);
    }

    /**
     * 过滤掉标识符
     *
     * @param path 字段路径
     * @return 值
     */
    public static String filterParseFlag(String path) {
        if (hasParseFlag(path)) {
            // 过滤掉#{...}
            return path.substring(2, path.length() -1);
        }

        return path;
    }

    public static String getRegex() {
        return "#\\{(.*?)}";
    }

    /**
     * 解析路径（将路径中#{...}解析成值）
     *
     * @param path 路径
     * @return 解析后的路径
     */
    public static String[] parsePath(String path) {
        String regex2 = getRegex();
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(path);
        while (matcher2.find()) {
            String paramStr = matcher2.group();
            if (paramStr.length() <= 3) {
                continue;
            }

            String paramFieldStr = paramStr.substring(2, paramStr.length() -1);
            Object value = DataFindHandler.find(paramFieldStr);
            if (value != null) {
                path = path.replace(paramStr, value.toString());
            }
        }

        return splitPath(path);
    }

    private static String[] splitPath(String path) {
        char[] chars = path.toCharArray();

        List<String> pathList = new ArrayList<>();
        StringBuilder currPath = new StringBuilder();
        boolean inSquareBrackets = false;
        for (char item : chars) {
            if (item == '[') {
                inSquareBrackets = true;
            } else if (item == ']') {
                inSquareBrackets = false;
            } else if (!inSquareBrackets && item == '.') {
                pathList.add(currPath.toString());
                currPath = new StringBuilder();
                continue;
            }

            currPath.append(item);
        }
        pathList.add(currPath.toString());

        return pathList.toArray(new String[0]);
    }
}
