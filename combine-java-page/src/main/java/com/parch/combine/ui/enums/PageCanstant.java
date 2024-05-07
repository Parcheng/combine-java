package com.parch.combine.ui.enums;

public interface PageCanstant {

    String SYSTEM_URL_FLAG = "${systemUrl}";

    String BASE_URL_FLAG = "${baseUrl}";

    default String buildDefaultTemplatePath(String pageType) {
        return "/template/elements/" + pageType.toLowerCase() + "_template.json";
    }
}
