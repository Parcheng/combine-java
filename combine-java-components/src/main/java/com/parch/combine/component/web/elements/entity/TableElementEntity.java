package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.TableSettings;
import com.parch.combine.component.web.ElementDomConfig;
import java.util.List;

/**
 * 配置类
 */
public class TableElementEntity extends ElementEntity<TableSettings> {

    private ElementDomConfig external;

    private ElementDomConfig table;

    private ElementDomConfig head;

    private ElementDomConfig headRow;

    private ElementDomConfig headCol;

    private ElementDomConfig body;

    private TableRowDomConfig row;

    private ElementDomConfig col;

    private ElementDomConfig checkbox;

    public TableElementEntity() {
        super(ElementTypeEnum.TABLE);
    }

    /**
     * 页面元素
     */
    public static class TableRowDomConfig extends ElementDomConfig {

        /**
         * 交替Class属性
         */
        public List<String> alternateClass;

        public List<String> getAlternateClass() {
            return alternateClass;
        }

        public void setAlternateClass(List<String> alternateClass) {
            this.alternateClass = alternateClass;
        }
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
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
