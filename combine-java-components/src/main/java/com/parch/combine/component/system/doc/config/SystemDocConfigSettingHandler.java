package com.parch.combine.component.system.doc.config;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;

/**
 * 设置处理器
 */
public class SystemDocConfigSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("doc.config", "获取系统配置组件", false, SystemDocConfigComponent.class);
        // builder.addDesc("获取系统配置组");

        builder.setResult("系统配置信息对象", false);
        return builder.get();
    }
}
