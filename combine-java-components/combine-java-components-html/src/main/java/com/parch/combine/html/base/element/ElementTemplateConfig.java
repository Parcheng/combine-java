package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.DomConfig;

public abstract class ElementTemplateConfig {

    @Field(key = "id", name = "元素模板ID", type = FieldTypeEnum.ID)
    private String id;

    @Field(key = "external", name = "外部DOM配置", type = FieldTypeEnum.CONFIG)
    @FieldRef(DomConfig.class)
    private DomConfig external;

    public void init() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DomConfig getExternal() {
        return external;
    }

    public void setExternal(DomConfig external) {
        this.external = external;
    }
}
