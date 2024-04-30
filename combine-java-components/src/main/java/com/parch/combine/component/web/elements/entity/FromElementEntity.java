package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.FromSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class FromElementEntity extends ElementEntity<FromSettings> {

    private ElementDomConfig external;

    private ElementDomConfig from;

    private ElementDomConfig item;

    private ElementDomConfig label;

    private ElementDomConfig content;

    public FromElementEntity() {
        super(ElementTypeEnum.FROM);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
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
