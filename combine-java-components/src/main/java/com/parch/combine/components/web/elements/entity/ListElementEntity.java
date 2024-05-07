package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.ListSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "列表页面元素", desc = "当 TYPE = LIST 时的参数列表")
public class ListElementEntity extends ElementEntity<ListSettings> {

    @Field(key = "list", name = "列表DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig list;

    @Field(key = "item", name = "列表项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig item;

    @Field(key = "defaultText", name = "列表为空时显示文本的DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig defaultText;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = ListSettings.class)
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
