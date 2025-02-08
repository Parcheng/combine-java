package com.parch.combine.gui.core.style;

import com.parch.combine.core.common.base.IMerge;
import com.parch.combine.core.component.settings.annotations.FieldSelect;
import com.parch.combine.gui.core.style.config.ElementBorderConfig;
import com.parch.combine.gui.core.style.config.ElementFontConfig;
import com.parch.combine.gui.core.style.config.ElementGridConfig;
import com.parch.combine.gui.core.style.config.ElementSizeConfig;
import com.parch.combine.core.component.settings.annotations.CommonObject;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.enums.LayoutTypeEnum;

@CommonObject(name = ElementObjectConstant.GUI_ELEMENT_NAME)
public class ElementConfig implements IMerge<ElementConfig> {

    @Field(key = "layout", name = "布局类型", type = FieldTypeEnum.SELECT, defaultValue = "GRID")
    @FieldSelect(enumClass = LayoutTypeEnum.class)
    private String layout;

    @Field(key = "fgColor", name = "前景色（字体）", type = FieldTypeEnum.TEXT)
    private String fgColor;

    @Field(key = "bgColor", name = "背景色", type = FieldTypeEnum.TEXT)
    private String bgColor;

    @Field(key = "opaque", name = "是否为不透明的", type = FieldTypeEnum.BOOLEAN)
    private Boolean opaque;

    @Field(key = "size", name = "尺寸配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementSizeConfig.class)
    @FieldRef(ElementSizeConfig.class)
    private ElementSizeConfig size;

    @Field(key = "border", name = "边框配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementBorderConfig.class)
    @FieldRef(ElementBorderConfig.class)
    private ElementBorderConfig border;

    @Field(key = "font", name = "字体配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementFontConfig.class)
    @FieldRef(ElementFontConfig.class)
    private ElementFontConfig font;

    @Field(key = "grid", name = "网格布局配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementGridConfig.class)
    @FieldRef(ElementGridConfig.class)
    private ElementGridConfig grid;

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

    public Boolean getOpaque() {
        return opaque;
    }

    public void setOpaque(Boolean opaque) {
        this.opaque = opaque;
    }

    public ElementGridConfig getGrid() {
        return grid;
    }

    public void setGrid(ElementGridConfig grid) {
        this.grid = grid;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }
}
