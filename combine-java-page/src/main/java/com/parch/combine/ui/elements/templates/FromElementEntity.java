package com.parch.combine.ui.elements.templates;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.FromSettings;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "表单页面元素", desc = "当 TYPE = FROM 时的参数列表")
public class FromElementEntity extends ElementEntity<FromSettings> {

    @Field(key = "from", name = "表单DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig from;

    @Field(key = "item", name = "表单项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig item;

    @Field(key = "label", name = "标签DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig label;

    @Field(key = "content", name = "内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig content;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = FromSettings.class)
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
