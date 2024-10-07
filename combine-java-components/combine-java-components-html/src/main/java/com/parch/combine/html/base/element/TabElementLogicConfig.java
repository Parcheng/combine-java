package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;
import com.parch.combine.html.base.element.core.SubElementConfig;

public interface TabElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "items", name = "页签项配置集合", type = FieldTypeEnum.OBJECT, isArray = true, isRequired = true)
        @FieldObject(TabItemSettings.class)
        TabItemSettings[] items();
    }

    interface TabItemSettings {

        @Field(key = "id", name = "页签ID", type = FieldTypeEnum.TEXT)
        String id();

        @Field(key = "title", name = "页签标题", type = FieldTypeEnum.TEXT, isRequired = true)
        String title();

        @Field(key = "show", name = "页签是否默认显示", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        Boolean show();

        @Field(key = "checked", name = "页签是否默认选中", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        Boolean checked();

        @Field(key = "hasClose", name = "页签是否允许关闭", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
        Boolean hasClose();

        @Field(key = "body", name = "页签内容配置", type = FieldTypeEnum.CONFIG, isRequired = true)
        @FieldObject(SubElementConfig.class)
        SubElementConfig body();
    }
}
