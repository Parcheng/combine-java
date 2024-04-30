package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.FromSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "表单页面元素", desc = "当 TYPE = FROM 时的参数列表")
public class FromElementEntity extends ElementEntity<FromSettings> {

    @ComponentField(key = "from", name = "表单DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig from;

    @ComponentField(key = "item", name = "表单项DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig item;

    @ComponentField(key = "label", name = "标签DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig label;

    @ComponentField(key = "content", name = "内容DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig content;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = FromSettings.class)
    private FromSettings settings;

    public FromElementEntity() {
        super(ElementTypeEnum.FROM);
    }

    @Override
    public FromSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(FromSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getItem() {
        return item;
    }

    public void setItem(ElementDomConfig item) {
        this.item = item;
    }

    public ElementDomConfig getContent() {
        return content;
    }

    public void setContent(ElementDomConfig content) {
        this.content = content;
    }

    public ElementDomConfig getFrom() {
        return from;
    }

    public void setFrom(ElementDomConfig from) {
        this.from = from;
    }

    public ElementDomConfig getLabel() {
        return label;
    }

    public void setLabel(ElementDomConfig label) {
        this.label = label;
    }
}
