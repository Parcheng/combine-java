package com.parch.combine.components.web.elements.entity;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.TableSettings;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "表格页面元素", desc = "当 TYPE = TABLE 时的参数列表")
public class TableElementEntity extends ElementEntity<TableSettings> {

    @ComponentField(key = "table", name = "表格DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig table;

    @ComponentField(key = "head", name = "表格头DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig head;

    @ComponentField(key = "headRow", name = "表格头行DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig headRow;

    @ComponentField(key = "headCol", name = "表格头单元格DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig headCol;

    @ComponentField(key = "body", name = "表格内容DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig body;

    @ComponentField(key = "row", name = "行DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = TableRowDomConfig.class)
    private TableRowDomConfig row;

    @ComponentField(key = "col", name = "单元格DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig col;

    @ComponentField(key = "checkbox", name = "多选DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig checkbox;

    @ComponentField(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = TableSettings.class)
    private TableSettings settings;

    public TableElementEntity() {
        super(ElementTypeEnum.TABLE);
    }

    /**
     * 页面元素
     */
    public static class TableRowDomConfig extends ElementDomConfig {

        @ComponentField(key = "alternateClass", name = "交替样式集合", type = FieldTypeEnum.TEXT, isArray = true)
        public List<String> alternateClass;

        public List<String> getAlternateClass() {
            return alternateClass;
        }

        public void setAlternateClass(List<String> alternateClass) {
            this.alternateClass = alternateClass;
        }
    }

    @Override
    public TableSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(TableSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getTable() {
        return table;
    }

    public void setTable(ElementDomConfig table) {
        this.table = table;
    }

    public ElementDomConfig getHead() {
        return head;
    }

    public void setHead(ElementDomConfig head) {
        this.head = head;
    }

    public ElementDomConfig getBody() {
        return body;
    }

    public void setBody(ElementDomConfig body) {
        this.body = body;
    }

    public TableRowDomConfig getRow() {
        return row;
    }

    public void setRow(TableRowDomConfig row) {
        this.row = row;
    }

    public ElementDomConfig getCol() {
        return col;
    }

    public void setCol(ElementDomConfig col) {
        this.col = col;
    }

    public ElementDomConfig getHeadCol() {
        return headCol;
    }

    public void setHeadCol(ElementDomConfig headCol) {
        this.headCol = headCol;
    }

    public ElementDomConfig getHeadRow() {
        return headRow == null ? row : headRow;
    }

    public void setHeadRow(ElementDomConfig headRow) {
        this.headRow = headRow;
    }

    public ElementDomConfig getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(ElementDomConfig checkbox) {
        this.checkbox = checkbox;
    }
}
