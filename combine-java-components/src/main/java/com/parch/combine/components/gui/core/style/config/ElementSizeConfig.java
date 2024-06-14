package com.parch.combine.components.gui.core.style.config;

import com.parch.combine.components.gui.core.style.ElementObjectCanstant;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

@CommonObject(key = ElementObjectCanstant.GUI_ELEMENT_SIZE, name = ElementObjectCanstant.GUI_ELEMENT_SIZE_NAME)
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
