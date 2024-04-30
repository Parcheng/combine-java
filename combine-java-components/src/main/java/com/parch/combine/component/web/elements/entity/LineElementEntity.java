package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.LineSettings;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class LineElementEntity extends ElementEntity<LineSettings> {

    private ElementDomConfig external;

    private ElementDomConfig line;

    public LineElementEntity() {
        super(ElementTypeEnum.LINE);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }

    public ElementDomConfig getLine() {
        return line;
    }

    public void setLine(ElementDomConfig line) {
        this.line = line;
    }
}
