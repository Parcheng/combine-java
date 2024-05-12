package com.parch.combine.core.ui.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.ui.base.UrlPathCanstant;
import com.parch.combine.core.ui.settings.PageSettingCanstant;
import com.parch.combine.core.ui.tools.UrlPathHelper;

public abstract class ElementTemplateConfig {

    @Field(key = "id", name = "元素模板ID", type = FieldTypeEnum.TEXT)
    private String id;

    @Field(key = "external", name = "外部DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
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
