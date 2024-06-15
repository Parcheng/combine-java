package com.parch.combine.web.service;

import com.parch.combine.ui.core.CombineJavaUIStarter;
import com.parch.combine.ui.core.service.ICombineJavaUIService;

public abstract class AbstractCombineJavaUIService {

    private ICombineJavaUIService service;

    public AbstractCombineJavaUIService(String configPath) {
        service = CombineJavaUIStarter.init(configPath);
    }

    public String getPage(String pageKey) {
        return service.getPage(pageKey);
    }
}
