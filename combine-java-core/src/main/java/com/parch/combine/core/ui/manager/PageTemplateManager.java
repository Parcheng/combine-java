package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.ui.base.ElementConfig;
import com.parch.combine.core.ui.base.ElementTemplateConfig;
import com.parch.combine.core.ui.handler.ElementClassHandler;
import java.util.Map;

public class PageTemplateManager extends AbsPreLoadConfigManager<ElementTemplateConfig>{

    @Override
    protected ElementTemplateConfig initConfig(String id, String type, Map<String, Object> configMap) {
        ElementConfig<?,?> elementConfig = ElementClassHandler.get(type);
        if (elementConfig == null) {
            PrintUtil.printError("【ui】【template】【" + id + "】【" + type + "】类型未注册");
            return null;
        }

        ElementTemplateConfig newTempConfig = elementConfig.buildTemplateConfig(configMap);
        if (newTempConfig == null) {
            return null;
        }

        newTempConfig.init();
        return newTempConfig;
    }
}
