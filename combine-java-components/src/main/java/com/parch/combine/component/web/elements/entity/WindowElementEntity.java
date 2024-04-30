package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.WindowSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class WindowElementEntity extends ElementEntity<WindowSettings> {

    private ElementDomConfig external;

    private ElementDomConfig window;

    private ElementDomConfig head;

    private ElementDomConfig headTitle;

    private ElementDomConfig headClose;

    public WindowElementEntity() {
        super(ElementTypeEnum.WINDOW);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
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
