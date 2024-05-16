package com.parch.combine.core.ui.tools;

import com.parch.combine.core.ui.base.UrlPathCanstant;
import com.parch.combine.core.ui.context.ConfigLoadingContext;
import com.parch.combine.core.ui.context.ConfigLoadingContextHandler;

public class UrlPathHelper {

    public static String replaceUrlFlag(String path) {
        ConfigLoadingContext context = ConfigLoadingContextHandler.getContext();
        path = path.replace(UrlPathCanstant.SYSTEM_URL_FLAG, context.getSystemUrl());
        return path.replace(UrlPathCanstant.BASE_URL_FLAG, context.getBaseUrl());
    }
}
