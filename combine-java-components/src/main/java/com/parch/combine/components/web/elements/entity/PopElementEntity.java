package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.PopSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "泡泡窗页面元素", desc = "当 TYPE = POP 时的参数列表")
public class PopElementEntity extends ElementEntity<PopSettings> {

    @Field(key = "pop", name = "泡泡窗DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig pop;

    @Field(key = "content", name = "泡泡窗内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig content;

    @Field(key = "close", name = "泡泡窗关闭标识DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig close;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = PopSettings.class)
    private PopSettings settings;

    public PopElementEntity() {
        super(ElementTypeEnum.POP);
    }

    @Override
    public PopSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(PopSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getClose() {
        return close;
    }

    public void setClose(ElementDomConfig close) {
        this.close = close;
    }

    public ElementDomConfig getContent() {
        return content;
    }

    public void setContent(ElementDomConfig content) {
        this.content = content;
    }

    public ElementDomConfig getPop() {
        return pop;
    }

    public void setPop(ElementDomConfig pop) {
        this.pop = pop;
    }
}
