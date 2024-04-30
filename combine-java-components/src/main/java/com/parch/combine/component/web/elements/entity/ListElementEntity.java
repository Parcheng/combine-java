package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.ListSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class ListElementEntity extends ElementEntity<ListSettings> {

    private ElementDomConfig external;

    private ElementDomConfig list;

    private ElementDomConfig item;

    private ElementDomConfig defaultText;

    public ListElementEntity() {
        super(ElementTypeEnum.LIST);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
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
