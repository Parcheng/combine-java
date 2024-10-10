package com.parch.combine.html.base.page;

import com.parch.combine.html.base.page.config.FlagConfig;

public interface HtmlPageInitConfig {

    // http://127.0.0.1:8888/combine
    String baseUrl();

    FlagConfig flagConfig();
}