package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.TagSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class TagElementEntity extends ElementEntity<TagSettings> {

    private ElementDomConfig external;

    private ElementDomConfig tag;

    private ElementDomConfig close;

    public TagElementEntity() {
        super(ElementTypeEnum.TAG);
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

    public ElementDomConfig getTag() {
        return tag;
    }

    public void setTag(ElementDomConfig tag) {
        this.tag = tag;
    }
}
