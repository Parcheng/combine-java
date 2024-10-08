package com.parch.combine.ui.elements.config;

import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

public class PopElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "pop", name = "泡泡窗DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig pop;

    @Field(key = "content", name = "泡泡窗内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig content;

    @Field(key = "close", name = "泡泡窗关闭标识DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig close;

    public DomConfig getClose() {
        return close;
    }

    public void setClose(DomConfig close) {
        this.close = close;
    }

    public DomConfig getContent() {
        return content;
    }

    public void setContent(DomConfig content) {
        this.content = content;
    }

    public DomConfig getPop() {
        return pop;
    }

    public void setPop(DomConfig pop) {
        this.pop = pop;
    }
}
