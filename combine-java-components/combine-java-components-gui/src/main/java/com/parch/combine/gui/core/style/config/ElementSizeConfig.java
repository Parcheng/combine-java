package com.parch.combine.gui.core.style.config;

import com.parch.combine.gui.core.style.ElementObjectConstant;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

@CommonObject(key = ElementObjectConstant.GUI_ELEMENT_SIZE, name = ElementObjectConstant.GUI_ELEMENT_SIZE_NAME)
public class ElementSizeConfig {

    @Field(key = "width", name = "宽度", type = FieldTypeEnum.NUMBER)
    private Integer width;

    @Field(key = "height", name = "高度", type = FieldTypeEnum.NUMBER)
    private Integer height;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
