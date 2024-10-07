package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface VideoElementLogicConfig extends ILogicConfig {

    @Field(key = "src", name = "视频地址", type = FieldTypeEnum.TEXT, isRequired = true)
    String src();

    @Field(key = "text", name = "不兼容时显示的文本提示信息", type = FieldTypeEnum.TEXT)
    String text();
}
