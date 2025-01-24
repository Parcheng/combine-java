package com.parch.combine.html.base.page;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.html.base.page.config.FlagConfig;
import com.parch.combine.html.base.page.config.HtmlPageTemplateConfig;

public interface HtmlPageInitConfig extends IInitConfig {

    @Field(key = "baseUrl", name = "根URL", type = FieldTypeEnum.TEXT, isRequired = true)
    String baseUrl();

    @Field(key = "flagConfig", name = "标识符配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(FlagConfig.class)
    FlagConfig flagConfig();

    @Field(key = "templateConfig", name = "页面模板配置", type = FieldTypeEnum.CONFIG)
    @FieldObject(HtmlPageTemplateConfig.class)
    HtmlPageTemplateConfig templateConfig();
}