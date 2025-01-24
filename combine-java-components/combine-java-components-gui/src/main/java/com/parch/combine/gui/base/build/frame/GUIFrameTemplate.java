package com.parch.combine.gui.base.build.frame;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.ElementConfig;

public class GUIFrameTemplate {

    @Field(key = "external", name = "外部元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig external;

    @Field(key = "top", name = "顶部样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig top;

    @Field(key = "bottom", name = "底部样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig bottom;

    @Field(key = "center", name = "中间样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig center;

    @Field(key = "left", name = "左侧样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig left;

    @Field(key = "right", name = "右侧样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig right;

    public ElementConfig getExternal() {
        return external;
    }

    public void setExternal(ElementConfig external) {
        this.external = external;
    }

    public ElementConfig getTop() {
        return top;
    }

    public void setTop(ElementConfig top) {
        this.top = top;
    }

    public ElementConfig getBottom() {
        return bottom;
    }

    public void setBottom(ElementConfig bottom) {
        this.bottom = bottom;
    }

    public ElementConfig getCenter() {
        return center;
    }

    public void setCenter(ElementConfig center) {
        this.center = center;
    }

    public ElementConfig getLeft() {
        return left;
    }

    public void setLeft(ElementConfig left) {
        this.left = left;
    }

    public ElementConfig getRight() {
        return right;
    }

    public void setRight(ElementConfig right) {
        this.right = right;
    }
}
