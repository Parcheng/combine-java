package com.parch.combine.component.system.doc.components;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;

/**
 * 设置处理器
 */
public class SystemDocSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("doc.components", "获取设置信息组件", false, SystemDocSettingsComponent.class);
        // builder.addDesc("获取所有组件设置信息");

        builder.setResult("所有组件设置信息集合", false);
        return builder.get();
    }
}
