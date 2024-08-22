package com.parch.combine.gui.core.style.config;

import com.parch.combine.gui.core.style.ElementObjectConstant;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

@CommonObject(name = ElementObjectConstant.GUI_ELEMENT_SIZE_NAME)
public class ElementSizeConfig {

    @Field(key = "width", name = "宽度", type = FieldTypeEnum.NUMBER)
    private Integer width;

    @Field(key = "height", name = "高度", type = FieldTypeEnum.NUMBER)
    private Integer height;

    @Field(key = "maxWidth", name = "最大宽度", type = FieldTypeEnum.NUMBER)
    private Integer maxWidth;

    @Field(key = "maxHeight", name = "最大高度", type = FieldTypeEnum.NUMBER)
    private Integer maxHeight;

    @Field(key = "minWidth", name = "最小宽度", type = FieldTypeEnum.NUMBER)
    private Integer minWidth;

    @Field(key = "minHeight", name = "最小高度", type = FieldTypeEnum.NUMBER)
    private Integer minHeight;

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

    public Integer getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(Integer maxWidth) {
        this.maxWidth = maxWidth;
    }

    public Integer getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Integer getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(Integer minWidth) {
        this.minWidth = minWidth;
    }

    public Integer getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(Integer minHeight) {
        this.minHeight = minHeight;
    }
}
