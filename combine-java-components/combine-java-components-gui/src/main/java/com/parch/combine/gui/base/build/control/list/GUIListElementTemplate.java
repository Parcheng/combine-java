package com.parch.combine.gui.base.build.control.list;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;

public class GUIListElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "list", name = "集合元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig list;

    @Field(key = "empty", name = "空列表提示元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig empty;

    @Field(key = "item", name = "集合每个元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig item;

    public ElementConfig getList() {
        return list;
    }

    public void setList(ElementConfig list) {
        this.list = list;
    }

    public ElementConfig getItem() {
        return item;
    }

    public void setItem(ElementConfig item) {
        this.item = item;
    }

    public ElementConfig getEmpty() {
        return empty;
    }

    public void setEmpty(ElementConfig empty) {
        this.empty = empty;
    }
}
