package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;

public interface PaginationElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "currPage", name = "当前页", type = FieldTypeEnum.TEXT, isRequired = true)
        String currPage();

        @Field(key = "maxPage", name = "最大页", type = FieldTypeEnum.TEXT, isRequired = true)
        String maxPage();

        @Field(key = "maxLength", name = "可选页最大长度", type = FieldTypeEnum.TEXT, defaultValue = "10")
        Integer maxLength();

        @Field(key = "triggers", name = "选页触发配置", type = FieldTypeEnum.COMPONENT, isArray = true)
        String[] triggers();
    }
}
