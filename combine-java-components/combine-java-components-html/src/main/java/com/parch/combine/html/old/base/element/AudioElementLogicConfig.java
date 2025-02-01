package com.parch.combine.html.old.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.old.base.element.core.ElementConfig;

public interface AudioElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "src", name = "音频来源", type = FieldTypeEnum.TEXT, isRequired = true)
        String src();

        @Field(key = "text", name = "不兼容时显示的文本提示信息", type = FieldTypeEnum.TEXT)
        String text();
    }
}
