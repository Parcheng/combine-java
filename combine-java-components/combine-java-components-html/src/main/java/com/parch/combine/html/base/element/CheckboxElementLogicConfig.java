package com.parch.combine.html.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;
import com.parch.combine.html.base.element.core.OptionElementConfig;

public interface CheckboxElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {
        @Field(key = "layout", name = "布局 INLINE | MULTILINE", type = FieldTypeEnum.TEXT, defaultValue = "MULTILINE")
        String layout();

        @Field(key = "key", name = "多选框的KEY属性，在获取数据时作为字段名", type = FieldTypeEnum.TEXT)
        String key();

        @Field(key = "value", name = "当前选中的值", type = FieldTypeEnum.TEXT)
        String value();

        @Field(key = "disabled", name = "是否禁选", type = FieldTypeEnum.BOOLEAN)
        Boolean disabled();

        @Field(key = "option", name = "多选项配置", type = FieldTypeEnum.CONFIG, isRequired = true)
        @FieldObject(OptionElementConfig.class)
        OptionElementConfig option();

        @Field(key = "triggers", name = "多选框触发配置（用于实现多级联动，暂未不支持使用", type = FieldTypeEnum.COMPONENT, isArray = true)
        String[] triggers();
    }
}
