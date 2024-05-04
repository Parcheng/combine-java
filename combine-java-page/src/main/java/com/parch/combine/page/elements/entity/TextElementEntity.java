package com.parch.combine.page.elements.entity;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.TextSettings;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "文本集页面元素", desc = "当 TYPE = TEXT 时的参数列表")
public class TextElementEntity extends ElementEntity<TextSettings> {

    @ComponentField(key = "line", name = "行通用DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig line;

    @ComponentField(key = "children", name = "子文本DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig children;

    @ComponentField(key = "levels", name = "每个层级的通用DOM配置集合", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private List<ElementDomConfig> lines;

    @ComponentField(key = "lines", name = "每行的DOM配置集合", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private List<ElementDomConfig> levels;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = TextSettings.class)
    private TextSettings settings;

    public TextElementEntity() {
        super(ElementTypeEnum.TEXT);
    }

    @Override
    public TextSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(TextSettings settings) {
        this.settings = settings;
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
