package com.parch.combine.gui.base.build.control.buttons;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.event.EventConfig;

public interface GUIControlButtonGroupLogicConfig extends GUIControlLogicConfig {

    @Field(key = "defaultText", name = "默认按钮文本", type = FieldTypeEnum.TEXT, defaultValue = "未知")
    String defaultText();

    @Field(key = "events", name = "事件配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(EventConfig.class)
    EventConfig[] events();

    @Field(key = "items", name = "按钮配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(ButtonItemConfig.class)
    ButtonItemConfig[] items();

    interface ButtonItemConfig {
        @Field(key = "text", name = "按钮文本", type = FieldTypeEnum.TEXT, isRequired = true)
        String text();

        @Field(key = "type", name = "按钮类型", type = FieldTypeEnum.TEXT)
        String type();

        @Field(key = "eventKeys", name = "按钮事件KEY文本", type = FieldTypeEnum.TEXT, isArray = true)
        String[] eventKeys();
    }
}
