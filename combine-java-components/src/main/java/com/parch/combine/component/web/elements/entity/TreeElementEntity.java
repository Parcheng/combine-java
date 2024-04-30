package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.TreeSettings;
import com.parch.combine.component.web.ElementDomConfig;
import java.util.List;

/**
 * 配置类
 */
public class TreeElementEntity extends ElementEntity<TreeSettings> {

    private ElementDomConfig external;

    private ElementDomConfig tree;

    private ElementDomConfig item;

    /**
     * 每个级别的配置
     */
    private List<ElementDomConfig> levelItems;

    private ElementDomConfig itemActive;

    private ElementDomConfig itemText;

    private ElementDomConfig itemTree;

    public TreeElementEntity() {
        super(ElementTypeEnum.TREE);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
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
