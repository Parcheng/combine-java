package com.parch.combine.core.ui.base;

public interface SourcePathCanstant {

    String SYSTEM_URL_FLAG = "${systemUrl}";

    String BASE_URL_FLAG = "${baseUrl}";

    default String buildDefaultTemplatePath(String pageType) {
        return "/template/elements/" + pageType.toLowerCase() + "_template.json";
    }
}
