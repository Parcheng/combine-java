package com.parch.combine.ui.elements.config;

import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;
import com.parch.combine.ui.core.settings.PageSettingCanstant;

import java.util.List;

public class TreeElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "tree", name = "树DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig tree;

    @Field(key = "item", name = "树项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig item;

    @Field(key = "levelItems", name = "每个层级的树项DOM配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private List<DomConfig> levelItems;

    @Field(key = "itemActive", name = "树项选中时DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig itemActive;

    @Field(key = "itemText", name = "树项文本DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig itemText;

    @Field(key = "itemTree", name = "树项子树DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig itemTree;

    public DomConfig getItemActive() {
        return itemActive;
    }

    public void setItemActive(DomConfig itemActive) {
        this.itemActive = itemActive;
    }

    public DomConfig getTree() {
        return tree;
    }

    public void setTree(DomConfig tree) {
        this.tree = tree;
    }

    public DomConfig getItemText() {
        return itemText;
    }

    public void setItemText(DomConfig itemText) {
        this.itemText = itemText;
    }

    public DomConfig getItemTree() {
        return itemTree;
    }

    public void setItemTree(DomConfig itemTree) {
        this.itemTree = itemTree;
    }

    public DomConfig getItem() {
        return item;
    }

    public void setItem(DomConfig item) {
        this.item = item;
    }

    public List<DomConfig> getLevelItems() {
        return levelItems;
    }

    public void setLevelItems(List<DomConfig> levelItems) {
        this.levelItems = levelItems;
    }
}
