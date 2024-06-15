package com.parch.combine.ui.core.tools;

import com.parch.combine.ui.core.base.UrlPathCanstant;
import com.parch.combine.ui.core.context.ConfigLoadingContext;
import com.parch.combine.ui.core.context.ConfigLoadingContextHandler;

public class UrlPathHelper {

    public static String replaceUrlFlag(String path) {
        ConfigLoadingContext context = ConfigLoadingContextHandler.getContext();
        path = path.replace(UrlPathCanstant.SYSTEM_URL_FLAG, context.getSystemUrl());
        return path.replace(UrlPathCanstant.BASE_URL_FLAG, context.getBaseUrl());
    }
}
