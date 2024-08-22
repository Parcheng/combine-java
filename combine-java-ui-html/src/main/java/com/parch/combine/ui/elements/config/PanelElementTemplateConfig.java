package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

public class PanelElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "title", name = "标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig title;

    @Field(key = "body", name = "内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig body;

    public DomConfig getTitle() {
        return title;
    }

    public void setTitle(DomConfig title) {
        this.title = title;
    }

    public DomConfig getBody() {
        return body;
    }

    public void setBody(DomConfig body) {
        this.body = body;
    }
}
