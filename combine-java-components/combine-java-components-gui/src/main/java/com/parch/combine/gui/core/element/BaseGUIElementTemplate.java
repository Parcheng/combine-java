package com.parch.combine.gui.core.element;

import com.parch.combine.core.common.base.IMerge;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;
import com.parch.combine.gui.core.style.config.ElementGridConfig;

public class BaseGUIElementTemplate implements IMerge<BaseGUIElementTemplate> {

    @Field(key = "external", name = "外部元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig external;

    public ElementConfig getExternal() {
        return external;
    }

    public ElementGridConfig getExternalGrid() {
        return external == null ? null : external.getGrid();
    }

    public void setExternal(ElementConfig external) {
        this.external = external;
    }
}
