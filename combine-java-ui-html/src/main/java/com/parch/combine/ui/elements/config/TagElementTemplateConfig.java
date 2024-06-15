package com.parch.combine.ui.elements.config;

import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;
import com.parch.combine.ui.core.settings.PageSettingCanstant;

public class TagElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "tag", name = "标签DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig tag;

    @Field(key = "close", name = "标签关闭标识DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig close;

    public DomConfig getClose() {
        return close;
    }

    public void setClose(DomConfig close) {
        this.close = close;
    }

    public DomConfig getTag() {
        return tag;
    }

    public void setTag(DomConfig tag) {
        this.tag = tag;
    }
}
