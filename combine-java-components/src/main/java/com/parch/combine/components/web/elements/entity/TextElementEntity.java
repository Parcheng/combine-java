package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.TextSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.component.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "文本集页面元素", desc = "当 TYPE = TEXT 时的参数列表")
public class TextElementEntity extends ElementEntity<TextSettings> {

    @Field(key = "line", name = "行通用DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig line;

    @Field(key = "children", name = "子文本DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig children;

    @Field(key = "levels", name = "每个层级的通用DOM配置集合", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private List<ElementDomConfig> lines;

    @Field(key = "lines", name = "每行的DOM配置集合", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private List<ElementDomConfig> levels;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = TextSettings.class)
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
