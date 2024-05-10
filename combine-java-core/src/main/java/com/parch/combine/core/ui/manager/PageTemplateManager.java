package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.manager.AbsPreLoadConfigManager;
import com.parch.combine.core.common.util.TypeConversionUtil;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import java.util.Map;

public class PageTemplateManager extends AbsPreLoadConfigManager<ElementTemplateConfig> {

    @Override
    protected <T extends ElementTemplateConfig> T initConfig(String id, String type, Map<String, Object> configMap, Class<T> clazz) {
        if (configMap == null) {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                return null;
            }
        }

        T newConfig = TypeConversionUtil.parseJava(configMap, clazz);
        newConfig.init();
        return newConfig;
    }
}
