package com.parch.combine.core.component.settings.builder;

import com.parch.combine.core.component.settings.config.ComponentClassifySetting;
import com.parch.combine.core.component.settings.config.ComponentSetting;
import java.util.List;

/**
 * 构建设置处理器
 */
public class ComponentClassifySettingBuilder {

    /**
     * 构造组件分类设置
     *
     * @param key KEY
     * @param name 名称
     * @return 组件设置
     */
    public static ComponentClassifySetting build(String key, String name, String packagePath) {
        return build(key, name, ComponentSettingScanBuilder.scanAndBuild(key, packagePath));
    }

    /**
     * 构造组件分类设置
     *
     * @param key KEY
     * @param name 名称
     * @return 组件设置
     */
    public static ComponentClassifySetting build(String key, String name, Class<?> baseClass) {
        return build(key, name, ComponentSettingScanBuilder.scanAndBuild(key, baseClass));
    }

    /**
     * 构造组件分类设置
     *
     * @param key KEY
     * @param name 名称
     * @return 组件设置
     */
    public static ComponentClassifySetting build(String key, String name, List<ComponentSetting> settings) {
        ComponentClassifySetting classify = new ComponentClassifySetting();
        classify.setKey(key);
        classify.setName(name);
        classify.setSettings(settings);

        classify.setCommons(CommonObjectSettingBuilder.get(key));
        CommonObjectSettingBuilder.clear(key);
        return classify;
    }
}
