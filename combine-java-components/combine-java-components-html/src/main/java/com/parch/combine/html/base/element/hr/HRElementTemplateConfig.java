package com.parch.combine.html.base.element.hr;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.element.ElementTemplateConfig;
import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

public class HRElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "hr", name = "线DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig hr;

    public DomConfig getHr() {
        return hr;
    }

    public void setHr(DomConfig hr) {
        this.hr = hr;
    }
}
