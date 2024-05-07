package com.parch.combine.ui.base;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.enums.PageCanstant;
import com.parch.combine.ui.enums.PageSettingCanstant;
import com.parch.combine.ui.old.ElementDomConfig;

public class TemplateConfig {

    private String id;

    private String tempPath;

    @Field(key = "external", name = "外部DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private ElementDomConfig external;

    public TemplateConfig(String path) {
        this.tempPath = PageCanstant.SYSTEM_URL_FLAG + path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }
}
