package com.parch.combine.html.old.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.old.base.element.core.ElementConfig;

public interface TagElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "tagType", name = "样式类型（可选值取决于模板配置）", type = FieldTypeEnum.TEXT, isRequired = true)
        @FieldDesc("系统内置模板支持的类型：normal | success | info | primary | warn | error")
        String tagType();

        @Field(key = "size", name = "标签大小（可选值1-4）", type = FieldTypeEnum.NUMBER, defaultValue = "3")
        Integer size();

        @Field(key = "text", name = "文本内容", type = FieldTypeEnum.TEXT, isRequired = true)
        String text();
    }
}
