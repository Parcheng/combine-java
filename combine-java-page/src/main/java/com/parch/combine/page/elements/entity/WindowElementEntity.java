package com.parch.combine.page.elements.entity;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.WindowSettings;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "窗口页面元素", desc = "当 TYPE = WINDOW 时的参数列表")
public class WindowElementEntity extends ElementEntity<WindowSettings> {

    @ComponentField(key = "window", name = "窗口DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig window;

    @ComponentField(key = "head", name = "窗口标题DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig head;

    @ComponentField(key = "headTitle", name = "窗口标题文本DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig headTitle;

    @ComponentField(key = "headClose", name = "窗口标题关闭标识DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig headClose;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = WindowSettings.class)
    private WindowSettings settings;

    public WindowElementEntity() {
        super(ElementTypeEnum.WINDOW);
    }

    @Override
    public WindowSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(WindowSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getHead() {
        return head;
    }

    public void setHead(ElementDomConfig head) {
        this.head = head;
    }

    public ElementDomConfig getHeadTitle() {
        return headTitle;
    }

    public void setHeadTitle(ElementDomConfig headTitle) {
        this.headTitle = headTitle;
    }

    public ElementDomConfig getHeadClose() {
        return headClose;
    }

    public void setHeadClose(ElementDomConfig headClose) {
        this.headClose = headClose;
    }

    public ElementDomConfig getWindow() {
        return window;
    }

    public void setWindow(ElementDomConfig window) {
        this.window = window;
    }
}
