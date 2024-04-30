package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.ListSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "列表页面元素", desc = "当 TYPE = LIST 时的参数列表")
public class ListElementEntity extends ElementEntity<ListSettings> {

    @ComponentField(key = "list", name = "列表DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig list;

    @ComponentField(key = "item", name = "列表项DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig item;

    @ComponentField(key = "defaultText", name = "列表为空时显示文本的DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig defaultText;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = ListSettings.class)
    private ListSettings settings;

    public ListElementEntity() {
        super(ElementTypeEnum.LIST);
    }

    @Override
    public ListSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(ListSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getList() {
        return list;
    }

    public void setList(ElementDomConfig list) {
        this.list = list;
    }

    public ElementDomConfig getItem() {
        return item;
    }

    public void setItem(ElementDomConfig item) {
        this.item = item;
    }

    public ElementDomConfig getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(ElementDomConfig defaultText) {
        this.defaultText = defaultText;
    }
}
