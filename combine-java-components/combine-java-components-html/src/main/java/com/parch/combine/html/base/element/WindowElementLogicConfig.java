package com.parch.combine.html.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;
import com.parch.combine.html.base.element.core.SubElementConfig;

public interface WindowElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
        String title();

        @Field(key = "show", name = "默认显示窗口", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        Boolean show();

        @Field(key = "size", name = "尺寸（宽度）px", type = FieldTypeEnum.NUMBER, defaultValue = "200")
        Integer size();

        @Field(key = "body", name = "内容配置", type = FieldTypeEnum.CONFIG, isRequired = true)
        @FieldObject(SubElementConfig.class)
        SubElementConfig body();

        @Field(key = "closeTriggers", name = "窗口关闭的触发配置", type = FieldTypeEnum.COMPONENT, isArray = true)
        String[] closeTriggers();
    }
}
