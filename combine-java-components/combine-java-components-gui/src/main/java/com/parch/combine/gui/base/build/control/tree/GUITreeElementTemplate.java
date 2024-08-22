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
    @FieldRef(ElementConfig.class)
    private ElementConfig[] texts;

    @Field(key = "children", name = "子元素集合外部样式配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig children;

    public ElementConfig[] getTexts() {
        return texts;
    }

    public void setTexts(ElementConfig[] texts) {
        this.texts = texts;
    }

    public ElementConfig getChildren() {
        return children;
    }

    public void setChildren(ElementConfig children) {
        this.children = children;
    }
}
