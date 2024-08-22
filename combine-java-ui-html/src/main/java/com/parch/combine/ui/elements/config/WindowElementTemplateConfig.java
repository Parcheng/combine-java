package com.parch.combine.ui.elements.config;

import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

public class WindowElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "window", name = "窗口DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig window;

    @Field(key = "head", name = "窗口标题DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig head;

    @Field(key = "headTitle", name = "窗口标题文本DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig headTitle;

    @Field(key = "headClose", name = "窗口标题关闭标识DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig headClose;

    @Field(key = "body", name = "窗口内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig body;

    public DomConfig getHead() {
        return head;
    }

    public void setHead(DomConfig head) {
        this.head = head;
    }

    public DomConfig getHeadTitle() {
        return headTitle;
    }

    public void setHeadTitle(DomConfig headTitle) {
        this.headTitle = headTitle;
    }

    public DomConfig getHeadClose() {
        return headClose;
    }

    public void setHeadClose(DomConfig headClose) {
        this.headClose = headClose;
    }

    public DomConfig getWindow() {
        return window;
    }

    public void setWindow(DomConfig window) {
        this.window = window;
    }

    public DomConfig getBody() {
        return body;
    }

    public void setBody(DomConfig body) {
        this.body = body;
    }
}
