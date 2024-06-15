package com.parch.combine.gui.base.core.style;

import com.parch.combine.gui.base.core.style.config.ElementSizeConfig;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

@CommonObject(key = ElementObjectConstant.GUI_ELEMENT, name = ElementObjectConstant.GUI_ELEMENT_NAME)
public class ElementConfig {

    @Field(key = "size", name = "尺寸配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementSizeConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT_SIZE)
    private ElementSizeConfig size;

    public ElementSizeConfig getSize() {
        return size;
    }

    public void setSize(ElementSizeConfig size) {
        this.size = size;
    }
}
