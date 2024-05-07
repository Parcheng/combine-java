package com.parch.combine.components.file.helper;

import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.net.URL;

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
    public static String getFinalPath(boolean useResourcePath, String basePath, String path) {
        String base = CheckEmptyUtil.EMPTY;
        if (useResourcePath) {
            URL systemResource = ClassLoader.getSystemResource("");
            if (systemResource != null) {
                base = systemResource.getPath();
            } else {
                URL currClassUrl = FilePathHelper.class.getResource("");
                base = currClassUrl.getPath().substring(6).replace("/com/parch/combine/components/file/helper/", "");
            }
        }

        return base + basePath + path;

//        String classLoaderPath = useResourcePath ? ClassLoader.getSystemResource("").getPath() : "";
//        return classLoaderPath + basePath + path;
    }
}
