package com.parch.combine.html.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;

public interface ButtonElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "items", name = "按钮配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
        @FieldObject(ButtonItemSettings.class)
        ButtonItemSettings[] items();
    }

    interface ButtonItemSettings {

        @Field(key = "type", name = "按钮样式类型（要与与模板一致）", type = FieldTypeEnum.TEXT, isRequired = true)
        String type();

        @Field(key = "size", name = "按钮大小（可选值1-4）", type = FieldTypeEnum.NUMBER, isRequired = true)
        Integer size();

        @Field(key = "text", name = "按钮文本", type = FieldTypeEnum.TEXT)
        String text();

        @Field(key = "triggers", name = "按钮触发配置", type = FieldTypeEnum.COMPONENT, isArray = true)
        String[] triggers();
    }
}
