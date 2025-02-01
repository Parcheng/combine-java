package com.parch.combine.html.old.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.old.base.element.core.ElementConfig;
import com.parch.combine.html.old.base.element.core.OptionElementConfig;

public interface SelectElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "key", name = "下拉框KEY属性，在获取数据时作为字段名", type = FieldTypeEnum.TEXT)
        String key();

        @Field(key = "value", name = "当前选中的值", type = FieldTypeEnum.TEXT)
        String value();

        @Field(key = "defaultText", name = "默认显示文本", type = FieldTypeEnum.TEXT)
        String defaultText();

        @Field(key = "defaultValue", name = "默认选择的值", type = FieldTypeEnum.TEXT)
        String defaultValue();

        @Field(key = "option", name = "下拉项配置", type = FieldTypeEnum.CONFIG)
        @FieldObject(OptionElementConfig.class)
        OptionElementConfig option();

        @Field(key = "triggers", name = "下拉框触发配置（用于实现多级联动，暂未不支持使用）", type = FieldTypeEnum.COMPONENT, isArray = true)
        String[] triggers();
    }
}
