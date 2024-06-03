package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

public class TitleElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "h1", name = "级别一标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig h1;

    @Field(key = "h2", name = "级别二标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig h2;

    @Field(key = "h3", name = "级别三标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig h3;

    @Field(key = "h4", name = "级别四标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig h4;

    @Field(key = "h5", name = "级别五标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig h5;

    @Field(key = "h6", name = "级别六标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig h6;

    @Field(key = "top", name = "上分割线DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig top;

    @Field(key = "bottom", name = "下分割线DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig bottom;

    public DomConfig getH1() {
        return h1;
    }

    public void setH1(DomConfig h1) {
        this.h1 = h1;
    }

    public DomConfig getH2() {
        return h2;
    }

    public void setH2(DomConfig h2) {
        this.h2 = h2;
    }

    public DomConfig getH3() {
        return h3;
    }

    public void setH3(DomConfig h3) {
        this.h3 = h3;
    }

    public DomConfig getH4() {
        return h4;
    }

    public void setH4(DomConfig h4) {
        this.h4 = h4;
    }

    public DomConfig getH5() {
        return h5;
    }

    public void setH5(DomConfig h5) {
        this.h5 = h5;
    }

    public DomConfig getH6() {
        return h6;
    }

    public void setH6(DomConfig h6) {
        this.h6 = h6;
    }

    public DomConfig getTop() {
        return top;
    }

    public void setTop(DomConfig top) {
        this.top = top;
    }

    public DomConfig getBottom() {
        return bottom;
    }

    public void setBottom(DomConfig bottom) {
        this.bottom = bottom;
    }
}
