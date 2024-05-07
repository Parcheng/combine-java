package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.TitleSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "标题页面元素", desc = "当 TYPE = TITLE 时的参数列表")
public class TitleElementEntity extends ElementEntity<TitleSettings> {

    @Field(key = "h1", name = "级别一标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig h1;

    @Field(key = "h2", name = "级别二标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig h2;

    @Field(key = "h3", name = "级别三标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig h3;

    @Field(key = "h4", name = "级别四标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig h4;

    @Field(key = "h5", name = "级别五标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig h5;

    @Field(key = "h6", name = "级别六标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig h6;

    @Field(key = "top", name = "上分割线DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig top;

    @Field(key = "bottom", name = "下分割线DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig bottom;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = TitleSettings.class)
    private TitleSettings settings;

    public TitleElementEntity() {
        super(ElementTypeEnum.TITLE);
    }

    @Override
    public TitleSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(TitleSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getH1() {
        return h1;
    }

    public void setH1(ElementDomConfig h1) {
        this.h1 = h1;
    }

    public ElementDomConfig getH2() {
        return h2;
    }

    public void setH2(ElementDomConfig h2) {
        this.h2 = h2;
    }

    public ElementDomConfig getH3() {
        return h3;
    }

    public void setH3(ElementDomConfig h3) {
        this.h3 = h3;
    }

    public ElementDomConfig getH4() {
        return h4;
    }

    public void setH4(ElementDomConfig h4) {
        this.h4 = h4;
    }

    public ElementDomConfig getH5() {
        return h5;
    }

    public void setH5(ElementDomConfig h5) {
        this.h5 = h5;
    }

    public ElementDomConfig getH6() {
        return h6;
    }

    public void setH6(ElementDomConfig h6) {
        this.h6 = h6;
    }

    public ElementDomConfig getTop() {
        return top;
    }

    public void setTop(ElementDomConfig top) {
        this.top = top;
    }

    public ElementDomConfig getBottom() {
        return bottom;
    }

    public void setBottom(ElementDomConfig bottom) {
        this.bottom = bottom;
    }
}
