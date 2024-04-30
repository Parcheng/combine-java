package com.parch.combine.component.tool.sleep;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class ToolSleepSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("lock", "休眠组件", false, ToolSleepComponent.class);
        // builder.addDesc("当前线程休眠");

        builder.addProperty(PropertyTypeEnum.LOGIC, "time", FieldTypeEnum.TEXT, "休眠时间",  true, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "time", "单位毫秒");


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "time", "1000", "表示休眠1000毫秒");


        builder.setResult("true 或报错", false);

        return builder.get();
    }
}
