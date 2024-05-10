package com.parch.combine.core.ui.tools;

import com.parch.combine.core.ui.context.ConfigLoadingContext;
import com.parch.combine.core.ui.context.ConfigLoadingContextHandler;

public class UrlPathHelper {

    public final static String SYSTEM_URL_FLAG = "${systemUrl}";

    public final static String BASE_URL_FLAG = "${baseUrl}";

    public static String buildDefaultTemplatePath(String pageType) {
        return "/template/elements/" + pageType.toLowerCase() + "_template.json";
    }

    public static String replaceUrlFlag(String path) {
        ConfigLoadingContext context = ConfigLoadingContextHandler.getContext();
        path = path.replace(SYSTEM_URL_FLAG, context.getSystemUrl());
        return path.replace(BASE_URL_FLAG, context.getBaseUrl());
    }
}
