package com.parch.combine.gui.core.style.config;

import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.ElementObjectConstant;
import com.parch.combine.gui.core.style.enums.LayoutAlignTypeEnum;
import com.parch.combine.gui.core.style.enums.LayoutAxisEnum;
import com.parch.combine.gui.core.style.enums.LayoutTypeEnum;

@CommonObject(key = ElementObjectConstant.GUI_ELEMENT_LAYOUT, name = ElementObjectConstant.GUI_ELEMENT_LAYOUT_NAME)
public class ElementLayoutConfig {

    @Field(key = "type", name = "布局管理器类型", type = FieldTypeEnum.SELECT, defaultValue = "FLOW")
    @FieldSelect(enumClass = LayoutTypeEnum.class)
    private String type;

    @Field(key = "align", name = "对齐", type = FieldTypeEnum.SELECT, defaultValue = "LEFT")
    @FieldDesc("type 为 FLOW 时生效")
    @FieldSelect(enumClass = LayoutAlignTypeEnum.class)
    private String align;

    @Field(key = "hgap", name = "水平间隙距离", type = FieldTypeEnum.NUMBER, defaultValue = "5")
    @FieldDesc("type 为 FLOW 时生效")
    private Integer hgap;

    @Field(key = "vgap", name = "垂直间隙距离", type = FieldTypeEnum.NUMBER, defaultValue = "5")
    @FieldDesc("type 为 FLOW 时生效")
    private Integer vgap;

    @Field(key = "axis", name = "内容排列方向", type = FieldTypeEnum.SELECT, defaultValue = "X")
    @FieldDesc("type 为 BOX 时生效")
    @FieldSelect(enumClass = LayoutAxisEnum.class)
    private String axis;

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public Integer getHgap() {
        return hgap;
    }

    public void setHgap(Integer hgap) {
        this.hgap = hgap;
    }

    public Integer getVgap() {
        return vgap;
    }

    public void setVgap(Integer vgap) {
        this.vgap = vgap;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAxis() {
        return axis;
    }

    public void setAxis(String axis) {
        this.axis = axis;
    }
}
