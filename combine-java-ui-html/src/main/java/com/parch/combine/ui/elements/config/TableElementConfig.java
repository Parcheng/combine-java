package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.base.element.SubElement;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "table", name = "表格元素", templateClass = TableElementTemplateConfig.class)
public class TableElementConfig extends ElementConfig<TableElementTemplateConfig> {

    @Field(key = "fieldNames", name = "每列对应的字段名配置集合", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> fieldNames;

    @Field(key = "dataFieldNames", name = "数据字段集合", type = FieldTypeEnum.TEXT)
    @FieldDesc("这些字段会被保存到表格数据中，但不显示在表格上，可以通过获取表格数据得到这些字段的值")
    private List<String> dataFieldNames;

    @Field(key = "headNames", name = "表头文本集合", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> headNames;

    @Field(key = "minLength", name = "最小行数（数据不够空行补全）", type = FieldTypeEnum.NUMBER, defaultValue = "10")
    private Integer minLength;

    @Field(key = "hasChecked", name = "是否允许选择行", type = FieldTypeEnum.BOOLEAN)
    private Boolean hasChecked;

    @Field(key = "hasIndex", name = "是否显示行号", type = FieldTypeEnum.BOOLEAN)
    private Boolean hasIndex;

    @Field(key = "rowOpts", name = "操作栏的操作元素配置", type = FieldTypeEnum.ELEMENT)
    // @FieldRef(key = PageSettingCanstant.ELEMENT_ENTITY_KEY)
    @SubElement
    public Object rowOpts;

    public TableElementConfig() {
        super(SystemElementPathTool.buildJsPath("table"), SystemElementPathTool.buildCssPath("table"),
                SystemElementPathTool.buildTemplatePath("table"), TableElementTemplateConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public List<String> getHeadNames() {
        return headNames;
    }

    public void setHeadNames(List<String> headNames) {
        this.headNames = headNames;
    }

    public Object getRowOpts() {
        return rowOpts;
    }

    public void setRowOpts(Object rowOpts) {
        this.rowOpts = rowOpts;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Boolean getHasChecked() {
        return hasChecked;
    }

    public void setHasChecked(Boolean hasChecked) {
        this.hasChecked = hasChecked;
    }

    public Boolean getHasIndex() {
        return hasIndex;
    }

    public void setHasIndex(Boolean hasIndex) {
        this.hasIndex = hasIndex;
    }

    public List<String> getDataFieldNames() {
        return dataFieldNames;
    }

    public void setDataFieldNames(List<String> dataFieldNames) {
        this.dataFieldNames = dataFieldNames;
    }
}
