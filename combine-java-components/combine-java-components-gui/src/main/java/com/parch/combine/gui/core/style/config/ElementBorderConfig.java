package com.parch.combine.gui.core.style.config;

import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.ElementObjectConstant;

@CommonObject(key = ElementObjectConstant.GUI_ELEMENT_BORDER, name = ElementObjectConstant.GUI_ELEMENT_BORDER_NAME)
public class ElementBorderConfig {

    @Field(key = "type", name = "边框类型", type = FieldTypeEnum.SELECT, defaultValue = "LINE")
    private String type;

    @Field(key = "color", name = "颜色编码", type = FieldTypeEnum.TEXT, defaultValue = "#FFFFFF")
    private String color;

    @Field(key = "size", name = "边框大小", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    private Integer size;

    @Field(key = "top", name = "上边距", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    private Integer top;

    @Field(key = "bottom", name = "下边距", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    private Integer bottom;

    @Field(key = "left", name = "左边距", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    private Integer left;

    @Field(key = "right", name = "右边距", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    private Integer right;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getBottom() {
        return bottom;
    }

    public void setBottom(Integer bottom) {
        this.bottom = bottom;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
