package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.manager.AbsPreLoadConfigManager;
import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.common.util.TypeConversionUtil;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import java.util.Map;

public class PageElementTemplateManager extends AbsPreLoadConfigManager<ElementTemplateConfig> {

    @Override
    protected <T extends ElementTemplateConfig> T initConfig(String id, String type, Map<String, Object> configMap, Class<T> clazz) {
        if (configMap == null) {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                PrintUtil.printError("【ui】【element-template】【" + id + "】【" + type + "】元素模板构建失败");
                return null;
            }
        }

        T newConfig = TypeConversionUtil.parseJava(configMap, clazz);
        newConfig.init();
        return newConfig;
    }
}
