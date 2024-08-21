package com.parch.combine.starter.service;

import com.parch.combine.ui.core.CombineJavaUIStarter;
import com.parch.combine.ui.core.service.ICombineJavaUIService;

/**
 * CombineJavaUI - Service基类
 */
public abstract class AbstractCombineJavaUIService {

    private ICombineJavaUIService service;

    public AbstractCombineJavaUIService(String configPath) {
        service = CombineJavaUIStarter.init(configPath);
    }

    public String getPage(String pageKey) {
        return service.getPage(pageKey);
    }
}
