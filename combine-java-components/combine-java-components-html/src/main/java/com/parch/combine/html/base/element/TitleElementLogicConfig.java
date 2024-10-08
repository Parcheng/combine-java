package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;

public interface TitleElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "text", name = "文本", type = FieldTypeEnum.TEXT, isRequired = true)
        String text();

        @Field(key = "level", name = "级别（1-6）", type = FieldTypeEnum.NUMBER, isRequired = true)
        Integer level();

        @Field(key = "top", name = "是否需要上面添加空行", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        Boolean top();

        @Field(key = "bottom", name = "是否需要下分割线", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        Boolean bottom();
    }
}
