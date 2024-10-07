package com.parch.combine.html.common.tool;

import com.parch.combine.html.common.canstant.UrlPathCanstant;

public class UrlPathHelper {

    public static String replaceUrlFlag(String path) {
        ConfigLoadingContext context = ConfigLoadingContextHandler.getContext();
        path = path.replace(UrlPathCanstant.SYSTEM_URL_FLAG, context.getSystemUrl());
        return path.replace(UrlPathCanstant.BASE_URL_FLAG, context.getBaseUrl());
    }
}
