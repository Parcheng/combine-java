package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.base.element.ElementLogicConfig;
import com.parch.combine.core.ui.handler.ElementClassHandler;

import java.util.*;

public class PageElementLogicManager {

    private static final Map<String, ElementLogicConfig> LOGIC_CONFIGS = new HashMap<>();

    private static final Map<String, Map<String, Object>> PRE_LOGIC_CONFIGS = new HashMap<>();

    public final static String ID_FIELD = "id";
    public final static String ELEMENT_TYPE_FIELD = "type";

    /**
     * 加载配置
     *
     * @param logicConfigs 配置集合
     * @return 初始化配置
     */
    protected boolean load(List<Map<String, Object>> logicConfigs) {
        if (CheckEmptyUtil.isNotEmpty(logicConfigs)) {
            for (Map<String, Object> item : logicConfigs) {
                String id = (String) item.get(PageElementLogicManager.ID_FIELD);
                String type = (String) item.get(PageElementLogicManager.ELEMENT_TYPE_FIELD);
                if (CheckEmptyUtil.isEmpty(type)) {
                    continue;
                }

                if (check(id, type)) {
                    continue;
                }

                PRE_LOGIC_CONFIGS.put(getKey(id, type), item);
            }
        }

        return true;
    }

    /**
     * 获取配置
     *
     * @param id 配置ID
     * @param type 组件类型
     * @return 初始化配置对象
     */
    public ElementLogicConfig get(String id, String type) {
        String key = getKey(id, type);
        ElementLogicConfig logicConfig = LOGIC_CONFIGS.get(key);
        if (logicConfig != null) {
            return logicConfig;
        }

        ElementConfig<?,?> elementConfig = ElementClassHandler.get(type);
        if (elementConfig == null) {
            return null;
        }

        Map<String, Object> preConfig = PRE_LOGIC_CONFIGS.get(key);
        ElementLogicConfig newLogicConfig = LOGIC_CONFIGS.put(getKey(id, type), elementConfig.buildLogicConfig(preConfig));;
        if (newLogicConfig == null) {
            return null;
        }

        newLogicConfig.init();
        LOGIC_CONFIGS.put(key, newLogicConfig);
        PRE_LOGIC_CONFIGS.remove(key);

        return newLogicConfig;
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
        return LOGIC_CONFIGS.containsKey(key) || PRE_LOGIC_CONFIGS.containsKey(key);
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
