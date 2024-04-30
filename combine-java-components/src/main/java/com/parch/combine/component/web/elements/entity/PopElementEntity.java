package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.PopSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class PopElementEntity extends ElementEntity<PopSettings> {

    private ElementDomConfig external;

    private ElementDomConfig pop;

    private ElementDomConfig content;

    private ElementDomConfig close;

    public PopElementEntity() {
        super(ElementTypeEnum.POP);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
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
