package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface PageTurningElementLogicConfig extends ILogicConfig {

    @Field(key = "currPage", name = "当前页", type = FieldTypeEnum.TEXT, isRequired = true)
    String currPage();

    @Field(key = "maxPage", name = "最大页", type = FieldTypeEnum.TEXT, isRequired = true)
    String maxPage();

    @Field(key = "triggers", name = "翻页触发配置", type = FieldTypeEnum.COMPONENT, isArray = true)
    String[] triggers();
}
