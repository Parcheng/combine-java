package com.parch.combine.ui.elements.templates;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.TableSettings;
import com.parch.combine.core.component.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "表格页面元素", desc = "当 TYPE = TABLE 时的参数列表")
public class TableElementEntity extends ElementEntity<TableSettings> {

    @Field(key = "table", name = "表格DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig table;

    @Field(key = "head", name = "表格头DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig head;

    @Field(key = "headRow", name = "表格头行DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig headRow;

    @Field(key = "headCol", name = "表格头单元格DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig headCol;

    @Field(key = "body", name = "表格内容DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig body;

    @Field(key = "row", name = "行DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = TableRowDomConfig.class)
    private TableRowDomConfig row;

    @Field(key = "col", name = "单元格DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig col;

    @Field(key = "checkbox", name = "多选DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig checkbox;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = TableSettings.class)
    private TableSettings settings;

    public TableElementEntity() {
        super(ElementTypeEnum.TABLE);
    }

    /**
     * 页面元素
     */
    public static class TableRowDomConfig extends ElementDomConfig {

        @Field(key = "alternateClass", name = "交替样式集合", type = FieldTypeEnum.TEXT, isArray = true)
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
