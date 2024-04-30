package com.parch.combine.core.settings.builder;

import com.parch.combine.core.settings.config.*;
import java.util.*;

/**
 * 组件公共对象构建类
 */
class ComponentCommonObjectBuilder {

    private Map<String, ComponentCommonObjectSetting> commonObjectMap = new LinkedHashMap<>(8);
    private Map<String, ComponentPropertiesSettingBuilder> commonObjectPropertyMap = new HashMap<>(8);

    /**
     * 构造函数
     */
    public void add(String key, String name, String desc) {
        ComponentCommonObjectSetting settings = new ComponentCommonObjectSetting();
        settings.setKey(key);
        settings.setName(name);
        settings.setDesc(desc == null ? null : Collections.singletonList(desc));
        commonObjectMap.put(key, settings);
        commonObjectPropertyMap.put(key, new ComponentPropertiesSettingBuilder(1));
    }

    /**
     * 获取设置信息
     *
     * @return 设置信息
     */
    public List<ComponentCommonObjectSetting> get() {
        List<ComponentCommonObjectSetting> commonObjectList = new ArrayList<>();
        for (ComponentCommonObjectSetting settings : commonObjectMap.values()) {
            settings.setProperties(commonObjectPropertyMap.get(settings.getKey()).get());
            commonObjectList.add(settings);
        }
        return commonObjectList;
    }

    public String getName(String key) {
        return commonObjectMap.get(key).getName();
    }

    public ComponentPropertiesSettingBuilder getPropertiesBuilder(String key) {
        return commonObjectPropertyMap.get(key);
    }
}
