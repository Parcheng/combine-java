package com.parch.combine.gui.base.build.control.table;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;

import java.util.List;

public class GUITableElementTemplate {

    @Field(key = "external", name = "外部元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig external;

    @Field(key = "table", name = "表格配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig table;

    @Field(key = "header", name = "表格头样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private ElementConfig header;

    @Field(key = "cell", name = "单元格样式配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(ElementConfig.class)
    @FieldRef(key = ElementObjectConstant.GUI_ELEMENT)
    private List<ElementConfig> cells;

    public ElementConfig getExternal() {
        return external;
    }

    public void setExternal(ElementConfig external) {
        this.external = external;
    }

    public ElementConfig getHeader() {
        return header;
    }

    public void setHeader(ElementConfig header) {
        this.header = header;
    }

    public ElementConfig getTable() {
        return table;
    }

    public void setTable(ElementConfig table) {
        this.table = table;
    }

    public List<ElementConfig> getCells() {
        return cells;
    }

    public void setCells(List<ElementConfig> cells) {
        this.cells = cells;
    }
}
