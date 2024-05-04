package com.parch.combine.page.elements.entity;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.TagSettings;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "标签页面元素", desc = "当 TYPE = TAG 时的参数列表")
public class TagElementEntity extends ElementEntity<TagSettings> {

    @ComponentField(key = "tag", name = "标签DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig tag;

    @ComponentField(key = "close", name = "标签关闭标识DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig close;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = TagSettings.class)
    private TagSettings settings;

    public TagElementEntity() {
        super(ElementTypeEnum.TAG);
    }

    @Override
    public TagSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(TagSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getClose() {
        return close;
    }

    public void setClose(ElementDomConfig close) {
        this.close = close;
    }

    public ElementDomConfig getTag() {
        return tag;
    }

    public void setTag(ElementDomConfig tag) {
        this.tag = tag;
    }
}
