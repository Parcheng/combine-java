package com.parch.combine.html.common.tool;

import com.parch.combine.html.common.canstant.UrlPathCanstant;

public class UrlPathHelper {

    public static String replaceUrlFlag(String path, String baseUrl) {
        return path.replace(UrlPathCanstant.BASE_URL_FLAG, baseUrl);
    }
}
