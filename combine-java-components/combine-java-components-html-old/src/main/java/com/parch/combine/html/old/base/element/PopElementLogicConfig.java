package com.parch.combine.html.old.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.old.base.element.core.ElementConfig;

public interface PopElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "popType", name = "样式类型（可选值取决于模板配置）", type = FieldTypeEnum.TEXT, isRequired = true)
        @FieldDesc("系统内置模板支持的类型：normal | success | info | warn | error")
        String popType();

        @Field(key = "size", name = "尺寸（宽度）px", type = FieldTypeEnum.NUMBER)
        Integer size();

        @Field(key = "text", name = "内容文本", type = FieldTypeEnum.TEXT, isRequired = true)
        String text();

        @Field(key = "hasClose", name = "是否支持关闭", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        Boolean hasClose();
    }
}
