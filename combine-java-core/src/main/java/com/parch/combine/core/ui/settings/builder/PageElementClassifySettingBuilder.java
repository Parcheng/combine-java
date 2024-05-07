package com.parch.combine.core.ui.settings.builder;

import com.parch.combine.core.component.settings.builder.ComponentSettingScanBuilder;
import com.parch.combine.core.component.settings.config.ComponentClassifySetting;
import com.parch.combine.core.component.settings.config.ComponentSetting;
import com.parch.combine.core.ui.settings.config.PageElementClassifySetting;

import java.util.List;

/**
 * 构建设置处理器
 */
public class PageElementClassifySettingBuilder {


    /**
     * 构造组件分类设置
     *
     * @param key KEY
     * @param name 名称
     * @return 组件设置
     */
    public static PageElementClassifySetting build(String key, String name, String packagePath) {
        return null; // build(key, name, ComponentSettingScanBuilder.scanAndBuild(key, packagePath));
    }

    /**
     * 构造组件分类设置
     *
     * @param key KEY
     * @param name 名称
     * @return 组件设置
     */
    public static PageElementClassifySetting build(String key, String name, Class<?> baseClass) {
        return null; // build(key, name, ComponentSettingScanBuilder.scanAndBuild(key, baseClass));
    }

    /**
     * 构造组件分类设置
     *
     * @param key KEY
     * @param name 名称
     * @return 组件设置
     */
    public static PageElementClassifySetting build(String key, String name, List<Object> settings) {
        PageElementClassifySetting classify = new PageElementClassifySetting();
        classify.setKey(key);
        classify.setName(name);
        classify.setSettings(settings);
        return classify;
    }
}
