package com.parch.combine.html.common.tool;

import com.parch.combine.html.common.canstant.UrlPathCanstant;

public class SystemElementPathTool {

    public static String buildJsPath(String baseUrl, String path) {
        return baseUrl + UrlPathCanstant.BASE_PATH + "elements/" + path + "_element.js";
    }

    public static String buildCssPath(String baseUrl, String path) {
        return baseUrl + UrlPathCanstant.BASE_PATH + "styles/" + path + "_element.css";
    }

    public static String buildTemplatePath(String baseUrl, String path) {
        return baseUrl + UrlPathCanstant.BASE_PATH + "templates/" + path + "_template.json";
    }
}
