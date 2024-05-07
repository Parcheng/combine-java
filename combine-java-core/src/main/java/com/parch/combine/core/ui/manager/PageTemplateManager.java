package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.handler.ElementClassHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageTemplateManager {

    /**
     * 初始化模板配置缓存池
     */
    private static final Map<String, ElementTemplateConfig> TEMPLATE_CONFIGS = new HashMap<>();

    /**
     * 预加载的缓存池
     */
    private static final Map<String, Map<String, Object>> PRE_TEMPLATE_CONFIGS = new HashMap<>();

    /**
     * 加载配置
     *
     * @param templateConfigs 配置集合
     * @return 初始化配置
     */
    protected boolean load(List<Map<String, Object>> templateConfigs) {
        if (CheckEmptyUtil.isNotEmpty(templateConfigs)) {
            for (Map<String, Object> item : templateConfigs) {
                String id = (String) item.get(PageElementLogicManager.ID_FIELD);
                String type = (String) item.get(PageElementLogicManager.ELEMENT_TYPE_FIELD);
                if (CheckEmptyUtil.isEmpty(type)) {
                    continue;
                }

                if (check(id, type)) {
                    continue;
                }

                PRE_TEMPLATE_CONFIGS.put(getKey(id, type), item);
            }
        }

        return true;
    }

    /**
     * 获取初始化配置
     *
     * @param id 配置ID
     * @param type 组件类型
     * @return 初始化配置对象
     */
    public ElementTemplateConfig get(String id, String type) {
        String key = getKey(id, type);
        ElementTemplateConfig tempConfig = TEMPLATE_CONFIGS.get(key);
        if (tempConfig != null) {
            return tempConfig;
        }

        ElementConfig<?,?> elementConfig = ElementClassHandler.get(type);
        if (elementConfig == null) {
            return null;
        }

        Map<String, Object> preConfig = PRE_TEMPLATE_CONFIGS.get(key);
        ElementTemplateConfig newTempConfig = TEMPLATE_CONFIGS.put(getKey(id, type), elementConfig.buildTemplateConfig(preConfig));;
        if (newTempConfig == null) {
            return null;
        }

        newTempConfig.init();
        TEMPLATE_CONFIGS.put(key, newTempConfig);
        PRE_TEMPLATE_CONFIGS.remove(key);

        return newTempConfig;
    }

    /**
     * 检测配置是否存在
     *
     * @param id 组件ID
     * @param type 组件类型
     * @return 是否存在
     */
    private boolean check(String id, String type) {
        String key = getKey(id, type);
        return TEMPLATE_CONFIGS.containsKey(key) || PRE_TEMPLATE_CONFIGS.containsKey(key);
    }

    /**
     * 获取Key
     *
     * @param id 组件ID
     * @param type 组件类型
     * @return KEY
     */
    private String getKey(String id, String type) {
        return type + (id == null ? CheckEmptyUtil.EMPTY : id);
    }
}
