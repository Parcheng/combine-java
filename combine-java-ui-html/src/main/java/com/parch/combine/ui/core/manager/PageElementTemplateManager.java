package com.parch.combine.ui.core.manager;

import com.parch.combine.core.common.manager.AbstractPreLoadConfigManager;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

import java.util.Map;

public class PageElementTemplateManager extends AbstractPreLoadConfigManager<ElementTemplateConfig> {

    @Override
    protected <T extends ElementTemplateConfig> T initConfig(String id, String type, Map<String, Object> configMap, Class<T> clazz) {
        if (configMap == null) {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                PrintUtil.printError("【ui】【element-template】【" + id + "】【" + type + "】元素模板构建失败");
                e.printStackTrace();
                return null;
            }
        }

        T newConfig = DataParseUtil.parseJava(configMap, clazz);
        newConfig.init();
        return newConfig;
    }
}
