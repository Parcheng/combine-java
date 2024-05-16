package com.parch.combine.ui.elements.tools;

import com.parch.combine.core.ui.base.UrlPathCanstant;

public class SystemElementPathTool {

    public static String buildJsPath(String path) {
        return UrlPathCanstant.SYSTEM_URL_FLAG + UrlPathCanstant.BASE_PATH + "elements/" + path + "_element.js";
    }

    public static String buildTemplatePath(String path) {
        return UrlPathCanstant.SYSTEM_URL_FLAG + UrlPathCanstant.BASE_PATH + "templates/" + path + "_template.json";
    }
}
