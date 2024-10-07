package com.parch.combine.html.base.template;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.template.core.DomConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;


public interface PaginationElementTemplateLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "样式模板配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    Config config();

    interface Config extends ElementTemplateConfig {

        @Field(key = "pagination", name = "分页DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig pagination();

        @Field(key = "item", name = "分页项DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig item();

        @Field(key = "itemActive", name = "分页项选中时DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig itemActive();

        @Field(key = "itemDisabled", name = "分页项不可选时DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig itemDisabled();

        @Field(key = "itemContentFirst", name = "首页项DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig itemContentFirst();

        @Field(key = "itemContentEnd", name = "尾页项DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig itemContentEnd();

        @Field(key = "itemContentNum", name = "选页项DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig itemContentNum();

        @Field(key = "itemContentSkip", name = "跳转指定页DOM配置（暂不支持）", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig itemContentSkip();

        @Field(key = "itemContentSkipInput", name = "跳转指定页输入框DOM配置（暂不支持）", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig itemContentSkipInput();
    }
}
