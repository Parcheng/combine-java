package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.SubElementConfig;

public interface PanelElementLogicConfig extends ILogicConfig {

    @Field(key = "title", name = "标题配置", type = FieldTypeEnum.TEXT)
    String title();

    @Field(key = "body", name = "页签内容配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(SubElementConfig.class)
    SubElementConfig body();
}
