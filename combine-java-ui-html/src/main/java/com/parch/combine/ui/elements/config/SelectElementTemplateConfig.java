package com.parch.combine.ui.elements.config;

import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

public class SelectElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "select", name = "下拉框DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig select;

    @Field(key = "option", name = "下拉框选项集合DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig option;

    public DomConfig getSelect() {
        return select;
    }

    public void setSelect(DomConfig select) {
        this.select = select;
    }

    public DomConfig getOption() {
        return option;
    }

    public void setOption(DomConfig option) {
        this.option = option;
    }
}
