package com.parch.combine.core.component.manager;

import com.parch.combine.core.common.canstant.FieldKeyCanstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.TypeConversionUtil;
import com.parch.combine.core.component.base.InitConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程初始化配置处理器
 */
public class InitConfigManager {

    /**
     * 初始化配置缓存池
     */
    private final Map<String, InitConfig> INIT_CONFIGS = new HashMap<>();

    /**
     * 预加载的初始化配置缓存池
     */
    private final Map<String, Map<String, Object>> PRE_INIT_CONFIGS = new HashMap<>();

    /**
     * 加载初始化配置
     *
     * @param initConfigs 初始化配置集合
     * @return 初始化配置
     */
    protected boolean load(List<Map<String, Object>> initConfigs) {
        if (CheckEmptyUtil.isNotEmpty(initConfigs)) {
            for (Map<String, Object> item : initConfigs) {
                String id = (String) item.get(FieldKeyCanstant.ID);
                String type = (String) item.get(FieldKeyCanstant.TYPE);
                if (CheckEmptyUtil.isEmpty(type)) {
                    continue;
                }

                if (check(id, type)) {
                    continue;
                }

                PRE_INIT_CONFIGS.put(getKey(id, type), item);
            }
        }

        return true;
    }

    /**
     * 获取初始化配置
     *
     * @param id 配置ID
     * @param type 组件类型
     * @param initConfigClass 配置Class对象
     * @param <T> 初始化配置类泛型
     * @return 初始化配置对象
     */
    @SuppressWarnings("unchecked")
    public <T extends InitConfig> T get(String id, String type, Class<T> initConfigClass) {
        String key = getKey(id, type);
        InitConfig initConfig = INIT_CONFIGS.get(key);
        if (initConfig != null) {
            return (T) initConfig;
        }

        T initConfigObj;
        Map<String, Object> preInitConfig = PRE_INIT_CONFIGS.get(key);
        if (preInitConfig == null) {
            try {
                initConfigObj = initConfigClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                return null;
            }
        } else {
            initConfigObj = TypeConversionUtil.parseJava(preInitConfig, initConfigClass);
        }

        initConfigObj.init();
        INIT_CONFIGS.put(key, initConfigObj);
        PRE_INIT_CONFIGS.remove(key);

        return initConfigObj;
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
        return INIT_CONFIGS.containsKey(key) || PRE_INIT_CONFIGS.containsKey(key);
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
