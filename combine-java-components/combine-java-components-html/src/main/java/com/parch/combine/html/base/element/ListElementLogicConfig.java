package com.parch.combine.html.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;
import com.parch.combine.html.base.element.core.SubElementConfig;

public interface ListElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "content", name = "列表项内容配置", type = FieldTypeEnum.CONFIG, isRequired = true)
        @FieldObject(SubElementConfig.class)
        SubElementConfig content();

        @Field(key = "defaultText", name = "列表为空时默认显示文本", type = FieldTypeEnum.TEXT)
        String defaultText();
    }
}
