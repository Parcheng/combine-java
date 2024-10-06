package com.parch.combine.html.base.element.table;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.element.ElementTemplateConfig;
import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.ui.core.base.element.ElementTemplateConfig;

import java.util.List;

public class TableElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "table", name = "表格DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig table;

    @Field(key = "head", name = "表格头DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig head;

    @Field(key = "headRow", name = "表格头行DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig headRow;

    @Field(key = "headCol", name = "表格头单元格DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig headCol;

    @Field(key = "body", name = "表格内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig body;

    @Field(key = "row", name = "行DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(TableRowDomConfig.class)
    private TableRowDomConfig row;

    @Field(key = "col", name = "单元格DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig col;

    @Field(key = "checkbox", name = "多选DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(DomConfig.class)
    private DomConfig checkbox;

    /**
     * 页面元素
     */
    public static class TableRowDomConfig extends DomConfig {

        @Field(key = "alternateClass", name = "交替样式集合", type = FieldTypeEnum.TEXT, isArray = true)
        public List<String> alternateClass;

        public List<String> getAlternateClass() {
            return alternateClass;
        }

        public void setAlternateClass(List<String> alternateClass) {
            this.alternateClass = alternateClass;
        }
    }

    public DomConfig getTable() {
        return table;
    }

    public void setTable(DomConfig table) {
        this.table = table;
    }

    public DomConfig getHead() {
        return head;
    }

    public void setHead(DomConfig head) {
        this.head = head;
    }

    public DomConfig getBody() {
        return body;
    }

    public void setBody(DomConfig body) {
        this.body = body;
    }

    public TableRowDomConfig getRow() {
        return row;
    }

    public void setRow(TableRowDomConfig row) {
        this.row = row;
    }

    public DomConfig getCol() {
        return col;
    }

    public void setCol(DomConfig col) {
        this.col = col;
    }

    public DomConfig getHeadCol() {
        return headCol;
    }

    public void setHeadCol(DomConfig headCol) {
        this.headCol = headCol;
    }

    public DomConfig getHeadRow() {
        return headRow == null ? row : headRow;
    }

    public void setHeadRow(DomConfig headRow) {
        this.headRow = headRow;
    }

    public DomConfig getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(DomConfig checkbox) {
        this.checkbox = checkbox;
    }
}
