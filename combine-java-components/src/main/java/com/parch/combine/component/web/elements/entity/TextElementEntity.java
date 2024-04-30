package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.TextSettings;
import com.parch.combine.component.web.ElementDomConfig;

import java.util.List;

/**
 * 配置类
 */
public class TextElementEntity extends ElementEntity<TextSettings> {

    private ElementDomConfig external;

    private ElementDomConfig line;

    private ElementDomConfig children;

    private List<ElementDomConfig> lines;

    private List<ElementDomConfig> levels;

    public TextElementEntity() {
        super(ElementTypeEnum.TEXT);
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

    public List<ElementDomConfig> getLines() {
        return lines;
    }

    public void setLines(List<ElementDomConfig> lines) {
        this.lines = lines;
    }

    public List<ElementDomConfig> getLevels() {
        return levels;
    }

    public void setLevels(List<ElementDomConfig> levels) {
        this.levels = levels;
    }

    public ElementDomConfig getChildren() {
        return children;
    }

    public void setChildren(ElementDomConfig children) {
        this.children = children;
    }
}
