package com.parch.combine.ui;

import com.parch.combine.ui.core.CombineJavaUILoader;
import com.parch.combine.ui.core.service.ICombineJavaUIService;

/**
 * CombineJavaUI - Service基类
 */
public abstract class BaseCombineJavaUIFunction {

    private ICombineJavaUIService service;

    public BaseCombineJavaUIFunction(String configPath) {
        service = CombineJavaUILoader.init(configPath);
    }

    public String getPage(String pageKey) {
        return service.getPage(pageKey);
    }
}
