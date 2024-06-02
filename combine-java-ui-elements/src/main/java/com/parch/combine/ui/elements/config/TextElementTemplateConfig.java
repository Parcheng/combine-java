package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

import java.util.List;

public class TextElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "line", name = "行通用DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig line;

    @Field(key = "children", name = "子文本DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig children;

    @Field(key = "levels", name = "每个层级的通用DOM配置集合", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private List<DomConfig> lines;

    @Field(key = "lines", name = "每行的DOM配置集合", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private List<DomConfig> levels;

    public DomConfig getLine() {
        return line;
    }

    public void setLine(DomConfig line) {
        this.line = line;
    }

    public List<DomConfig> getLines() {
        return lines;
    }

    public void setLines(List<DomConfig> lines) {
        this.lines = lines;
    }

    public List<DomConfig> getLevels() {
        return levels;
    }

    public void setLevels(List<DomConfig> levels) {
        this.levels = levels;
    }

    public DomConfig getChildren() {
        return children;
    }

    public void setChildren(DomConfig children) {
        this.children = children;
    }
}
