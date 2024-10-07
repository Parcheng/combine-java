package com.parch.combine.html.base.page.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface HtmlHeaderMetaConfig {

    @Field(key = "name", name = "页面的媒体信息名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String name();

    @Field(key = "content", name = "页面的媒体信息内容", type = FieldTypeEnum.TEXT, isRequired = true)
    String content();
}
