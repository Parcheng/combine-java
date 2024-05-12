package com.parch.combine.web.service;

import com.parch.combine.core.ui.CombineJavaUIStarter;
import com.parch.combine.core.ui.service.ICombineJavaUIService;

public abstract class AbstractCombineJavaUIService {

    private ICombineJavaUIService service;

    public AbstractCombineJavaUIService(String configPath) {
        service = CombineJavaUIStarter.init(configPath);
    }

    public String getPage(String pageKey) {
        return service.getPage(pageKey);
    }
}
