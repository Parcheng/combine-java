package com.parch.combine.html.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;

public interface HRElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "count", name = "分隔线数量", type = FieldTypeEnum.NUMBER, defaultValue = "1")
        Integer count();
    }
}
