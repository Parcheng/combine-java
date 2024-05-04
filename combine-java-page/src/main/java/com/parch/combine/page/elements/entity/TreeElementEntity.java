package com.parch.combine.page.elements.entity;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.TreeSettings;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "树页面元素", desc = "当 TYPE = TREE 时的参数列表")
public class TreeElementEntity extends ElementEntity<TreeSettings> {

    @ComponentField(key = "tree", name = "树DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig tree;

    @ComponentField(key = "item", name = "树项DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig item;

    @ComponentField(key = "levelItems", name = "每个层级的树项DOM配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private List<ElementDomConfig> levelItems;

    @ComponentField(key = "itemActive", name = "树项选中时DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig itemActive;

    @ComponentField(key = "itemText", name = "树项文本DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig itemText;

    @ComponentField(key = "itemTree", name = "树项子树DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig itemTree;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = TreeSettings.class)
    private TreeSettings settings;

    public TreeElementEntity() {
        super(ElementTypeEnum.TREE);
    }

    @Override
    public TreeSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(TreeSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getItemActive() {
        return itemActive;
    }

    public void setItemActive(ElementDomConfig itemActive) {
        this.itemActive = itemActive;
    }

    public ElementDomConfig getTree() {
        return tree;
    }

    public void setTree(ElementDomConfig tree) {
        this.tree = tree;
    }

    public ElementDomConfig getItemText() {
        return itemText;
    }

    public void setItemText(ElementDomConfig itemText) {
        this.itemText = itemText;
    }

    public ElementDomConfig getItemTree() {
        return itemTree;
    }

    public void setItemTree(ElementDomConfig itemTree) {
        this.itemTree = itemTree;
    }

    public ElementDomConfig getItem() {
        return item;
    }

    public void setItem(ElementDomConfig item) {
        this.item = item;
    }

    public List<ElementDomConfig> getLevelItems() {
        return levelItems;
    }

    public void setLevelItems(List<ElementDomConfig> levelItems) {
        this.levelItems = levelItems;
    }
}
