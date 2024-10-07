package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface HRElementLogicConfig extends ILogicConfig {

    @Field(key = "count", name = "分隔线数量", type = FieldTypeEnum.TEXT)
    Integer count();
}
