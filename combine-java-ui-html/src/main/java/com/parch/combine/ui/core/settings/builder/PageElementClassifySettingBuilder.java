package com.parch.combine.ui.core.settings.builder;

import com.parch.combine.ui.core.settings.config.PageElementClassifySetting;
import com.parch.combine.ui.core.settings.config.PageElementSetting;

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
        return build(key, name, PageElementSettingScanBuilder.scanAndBuild(key, packagePath));
    }

    /**
     * 构造组件分类设置
     *
     * @param key KEY
     * @param name 名称
     * @return 组件设置
     */
    public static PageElementClassifySetting build(String key, String name, Class<?> baseClass) {
        return build(key, name, PageElementSettingScanBuilder.scanAndBuild(key, baseClass));
    }

    /**
     * 构造组件分类设置
     *
     * @param key KEY
     * @param name 名称
     * @return 组件设置
     */
    public static PageElementClassifySetting build(String key, String name, List<PageElementSetting> settings) {
        PageElementClassifySetting classify = new PageElementClassifySetting();
        classify.setKey(key);
        classify.setName(name);
        classify.setSettings(settings);
        return classify;
    }
}
