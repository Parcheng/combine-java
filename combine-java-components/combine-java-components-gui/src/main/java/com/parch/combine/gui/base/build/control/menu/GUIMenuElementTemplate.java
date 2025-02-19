package com.parch.combine.gui.base.build.control.menu;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;

public class GUIMenuElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "title", name = "菜单标题样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig title;

    @Field(key = "bar", name = "菜单条元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig bar;

    @Field(key = "bar", name = "菜单条元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig[] subBars;

    @Field(key = "mainItem", name = "主菜单样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig mainItem;

    @Field(key = "item", name = "子菜单选择项样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig item;

    @Field(key = "itemActive", name = "菜单选中样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig itemActive;

    public ElementConfig getMainItem() {
        return mainItem;
    }

    public void setMainItem(ElementConfig mainItem) {
        this.mainItem = mainItem;
    }

    public ElementConfig getItem() {
        return item;
    }

    public void setItem(ElementConfig item) {
        this.item = item;
    }

    public ElementConfig getBar() {
        return bar;
    }

    public void setBar(ElementConfig bar) {
        this.bar = bar;
    }

    public ElementConfig getItemActive() {
        return itemActive;
    }

    public void setItemActive(ElementConfig itemActive) {
        this.itemActive = itemActive;
    }

    public ElementConfig getTitle() {
        return title;
    }

    public void setTitle(ElementConfig title) {
        this.title = title;
    }

    public ElementConfig[] getSubBars() {
        return subBars;
    }

    public void setSubBars(ElementConfig[] subBars) {
        this.subBars = subBars;
    }
}
