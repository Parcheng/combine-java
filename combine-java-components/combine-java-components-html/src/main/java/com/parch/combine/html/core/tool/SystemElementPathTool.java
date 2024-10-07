package com.parch.combine.html.core.tool;

import com.parch.combine.html.core.canstant.UrlPathCanstant;

public class SystemElementPathTool {

    public static String buildJsPath(String path) {
        return UrlPathCanstant.SYSTEM_URL_FLAG + UrlPathCanstant.BASE_PATH + "elements/" + path + "_element.js";
    }

    public static String buildCssPath(String path) {
        return UrlPathCanstant.SYSTEM_URL_FLAG + UrlPathCanstant.BASE_PATH + "styles/" + path + "_element.css";
    }

    public static String buildTemplatePath(String path) {
        return UrlPathCanstant.SYSTEM_URL_FLAG + UrlPathCanstant.BASE_PATH + "templates/" + path + "_template.json";
    }
}
