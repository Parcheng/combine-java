package com.parch.combine.core.ui.builder.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public class HtmlHeaderMetaConfig {

    @Field(key = "name", name = "页面的媒体信息名称", type = FieldTypeEnum.TEXT, isRequired = true)
    private String name;

    @Field(key = "content", name = "页面的媒体信息内容", type = FieldTypeEnum.TEXT, isRequired = true)
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
