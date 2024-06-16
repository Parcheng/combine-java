package com.parch.combine.gui.core.style;

import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.gui.core.style.config.ElementBorderConfig;
import com.parch.combine.gui.core.style.config.ElementFontConfig;
import com.parch.combine.gui.core.style.config.ElementLayoutConfig;
import com.parch.combine.gui.core.style.config.ElementSizeConfig;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.enums.AlignmentXEnum;
import com.parch.combine.gui.core.style.enums.AlignmentYEnum;

@CommonObject(key = ElementObjectConstant.GUI_ELEMENT, name = ElementObjectConstant.GUI_ELEMENT_NAME)
public class ElementConfig {

    @Field(key = "fgColor", name = "前景色（字体）", type = FieldTypeEnum.TEXT)
    private String fgColor;

    @Field(key = "bgColor", name = "背景色", type = FieldTypeEnum.TEXT)
    private String bgColor;

    @Field(key = "alignmentX", name = "在父容器中X轴的对齐方式", type = FieldTypeEnum.SELECT)
    @FieldSelect(enumClass = AlignmentXEnum.class)
    @FieldDesc("父容器的布局 type 为 BOX 时生效")
    private String alignmentX;

    @Field(key = "alignmentX", name = "在父容器中Y轴的对齐方式", type = FieldTypeEnum.SELECT)
    @FieldSelect(enumClass = AlignmentYEnum.class)
    @FieldDesc("父容器的布局 type 为 BOX 时生效")
    private String alignmentY;

    @Field(key = "layout", name = "布局配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementLayoutConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT_LAYOUT)
    private ElementLayoutConfig layout;

    @Field(key = "size", name = "尺寸配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementSizeConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT_SIZE)
    private ElementSizeConfig size;

    @Field(key = "border", name = "边框配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementBorderConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT_BORDER)
    private ElementBorderConfig border;

    @Field(key = "font", name = "字体配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementFontConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT_FONT)
    private ElementFontConfig font;

    public ElementSizeConfig getSize() {
        return size;
    }

    public void setSize(ElementSizeConfig size) {
        this.size = size;
    }

    public ElementBorderConfig getBorder() {
        return border;
    }

    public void setBorder(ElementBorderConfig border) {
        this.border = border;
    }

    public ElementFontConfig getFont() {
        return font;
    }

    public void setFont(ElementFontConfig font) {
        this.font = font;
    }

    public String getFgColor() {
        return fgColor;
    }

    public void setFgColor(String fgColor) {
        this.fgColor = fgColor;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public ElementLayoutConfig getLayout() {
        return layout;
    }

    public void setLayout(ElementLayoutConfig layout) {
        this.layout = layout;
    }

    public String getAlignmentX() {
        return alignmentX;
    }

    public void setAlignmentX(String alignmentX) {
        this.alignmentX = alignmentX;
    }

    public String getAlignmentY() {
        return alignmentY;
    }

    public void setAlignmentY(String alignmentY) {
        this.alignmentY = alignmentY;
    }
}
