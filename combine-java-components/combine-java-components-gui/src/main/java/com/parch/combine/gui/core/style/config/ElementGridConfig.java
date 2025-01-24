package com.parch.combine.gui.core.style.config;

import com.parch.combine.core.common.base.IMerge;
import com.parch.combine.core.component.settings.annotations.CommonObject;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldSelect;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.ElementObjectConstant;
import com.parch.combine.gui.core.style.enums.GridFillEnum;

@CommonObject(name = ElementObjectConstant.GUI_ELEMENT_GRID_NAME)
public class ElementGridConfig implements IMerge<ElementGridConfig> {

    @Field(key = "fill", name = "填充方向", type = FieldTypeEnum.SELECT, defaultValue = "NONE")
    @FieldSelect(enumClass = GridFillEnum.class)
    private String fill;

    @Field(key = "weightX", name = "X轴宽度占比", type = FieldTypeEnum.NUMBER)
    private Double weightX;

    @Field(key = "weightY", name = "Y轴占高度比", type = FieldTypeEnum.NUMBER)
    private Double weightY;

    @Field(key = "occupyX", name = "X轴占据列数", type = FieldTypeEnum.NUMBER)
    private Integer occupyX;

    @Field(key = "occupyY", name = "Y轴占据行数", type = FieldTypeEnum.NUMBER)
    private Integer occupyY;

    @Field(key = "positionX", name = "在X轴的列位置", type = FieldTypeEnum.NUMBER)
    private Integer positionX;

    @Field(key = "positionY", name = "在Y轴的行位置", type = FieldTypeEnum.NUMBER)
    private Integer positionY;

    public Double getWeightX() {
        return weightX;
    }

    public void setWeightX(Double weightX) {
        this.weightX = weightX;
    }

    public Double getWeightY() {
        return weightY;
    }

    public void setWeightY(Double weightY) {
        this.weightY = weightY;
    }

    public Integer getOccupyX() {
        return occupyX;
    }

    public void setOccupyX(Integer occupyX) {
        this.occupyX = occupyX;
    }

    public Integer getOccupyY() {
        return occupyY;
    }

    public void setOccupyY(Integer occupyY) {
        this.occupyY = occupyY;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }
}
