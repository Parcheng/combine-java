package com.parch.combine.gui.core.style.config;

import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.ElementObjectConstant;

@CommonObject(name = ElementObjectConstant.GUI_ELEMENT_BORDER_NAME)
public class ElementBorderConfig {

    @Field(key = "type", name = "边框类型", type = FieldTypeEnum.SELECT, defaultValue = "LINE")
    @FieldDesc("目前仅支持 LINE 类型")
    private String type;

    @Field(key = "color", name = "颜色编码", type = FieldTypeEnum.TEXT, defaultValue = "#FFFFFF")
    private String color;

    @Field(key = "size", name = "边框大小", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    private Integer size;

    @Field(key = "leftConfig", name = "左边框配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(SubBorderConfig.class)
    private SubBorderConfig leftConfig;

    @Field(key = "rightConfig", name = "右边框配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(SubBorderConfig.class)
    private SubBorderConfig rightConfig;

    @Field(key = "topConfig", name = "上边框配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(SubBorderConfig.class)
    private SubBorderConfig topConfig;

    @Field(key = "bottomConfig", name = "下边框配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(SubBorderConfig.class)
    private SubBorderConfig bottomConfig;

    @Field(key = "top", name = "上边距", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    private Integer top;

    @Field(key = "bottom", name = "下边距", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    private Integer bottom;

    @Field(key = "left", name = "左边距", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    private Integer left;

    @Field(key = "right", name = "右边距", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    private Integer right;

    public static class SubBorderConfig {

        @Field(key = "type", name = "边框类型", type = FieldTypeEnum.SELECT, defaultValue = "LINE")
        @FieldDesc("目前仅支持 LINE 类型")
        private String type;

        @Field(key = "color", name = "颜色编码", type = FieldTypeEnum.TEXT, defaultValue = "#FFFFFF")
        private String color;

        @Field(key = "size", name = "边框大小", type = FieldTypeEnum.NUMBER, defaultValue = "0")
        private Integer size;

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

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }
    }

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

    public SubBorderConfig getLeftConfig() {
        return leftConfig;
    }

    public void setLeftConfig(SubBorderConfig leftConfig) {
        this.leftConfig = leftConfig;
    }

    public SubBorderConfig getRightConfig() {
        return rightConfig;
    }

    public void setRightConfig(SubBorderConfig rightConfig) {
        this.rightConfig = rightConfig;
    }

    public SubBorderConfig getTopConfig() {
        return topConfig;
    }

    public void setTopConfig(SubBorderConfig topConfig) {
        this.topConfig = topConfig;
    }

    public SubBorderConfig getBottomConfig() {
        return bottomConfig;
    }

    public void setBottomConfig(SubBorderConfig bottomConfig) {
        this.bottomConfig = bottomConfig;
    }
}
