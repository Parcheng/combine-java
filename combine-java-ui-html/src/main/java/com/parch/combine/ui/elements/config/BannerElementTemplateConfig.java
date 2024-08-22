package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

public class BannerElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "close", name = "关闭标识DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig close;

    @Field(key = "img", name = "图片内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig img;

    @Field(key = "text", name = "文本内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig text;

    public DomConfig getClose() {
        return close;
    }

    public void setClose(DomConfig close) {
        this.close = close;
    }

    public DomConfig getImg() {
        return img;
    }

    public void setImg(DomConfig img) {
        this.img = img;
    }

    public DomConfig getText() {
        return text;
    }

    public void setText(DomConfig text) {
        this.text = text;
    }
}
