package com.parch.combine.page.elements.entity;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.PopSettings;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "泡泡窗页面元素", desc = "当 TYPE = POP 时的参数列表")
public class PopElementEntity extends ElementEntity<PopSettings> {

    @ComponentField(key = "pop", name = "泡泡窗DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig pop;

    @ComponentField(key = "content", name = "泡泡窗内容DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig content;

    @ComponentField(key = "close", name = "泡泡窗关闭标识DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig close;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = PopSettings.class)
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
