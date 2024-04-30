package com.parch.combine.component.file;

/**
 * 文件组件相关常量
 */
public class FilePathHelper {

    /**
     * 获取最终路径
     *
     * @param basePath 根路径
     * @param path 路径
     * @return 最终路径
     */
    public static String getFinalPath(String basePath, String path) {
        String classLoaderPath = ClassLoader.getSystemResource("").getPath();
        return classLoaderPath + basePath + path;
    }
}
