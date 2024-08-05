package com.parch.combine.gui.base.build.control.tree;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;

public class GUITreeElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "items", name = "每个层级样式配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig[] items;

    public ElementConfig[] getItems() {
        return items;
    }

    public void setItems(ElementConfig[] items) {
        this.items = items;
    }
}
