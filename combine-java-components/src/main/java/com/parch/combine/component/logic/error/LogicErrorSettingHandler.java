package com.parch.combine.component.logic.error;

import com.parch.combine.core.settings.handler.CompareGroupSettingHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class LogicErrorSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("error", "错误抛出组件", false, LogicErrorComponent.class);
        // builder.addDesc("抛出错误指定错误信息");

        builder.addProperty(PropertyTypeEnum.LOGIC, "items", FieldTypeEnum.GROUP, "配置项集合",  true, true);
        CompareGroupSettingHandler.set(PropertyTypeEnum.LOGIC, builder, "items");
        builder.addProperty(PropertyTypeEnum.LOGIC, "items.errorMsg", FieldTypeEnum.TEXT, "错误信息",  true, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "items.showMsg", FieldTypeEnum.TEXT, "可显示的错误信息",  true, false);


        CompareGroupSettingHandler.setEg(PropertyTypeEnum.LOGIC, builder, "items");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items.errorMsg", "空指针异常", "条件满足后执行会报错，返回的 errorMsg 为该配置项的信息");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items.showMsg", "系统内部错误", "条件满足后执行会报错，返回的 showMsg 为该配置项的信息");


        builder.setResult("配置的错误信息或 true", false);
        return builder.get();
    }
}
