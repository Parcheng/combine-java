package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.ui.base.SubConfig;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.SubElement;
import com.parch.combine.core.ui.settings.PageSettingCanstant;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;
import java.util.UUID;

@PageElement(key = "from", name = "表单元素", templateClass = FromElementTemplateConfig.class)
public class FromElementConfig extends ElementConfig<FromElementTemplateConfig> {

    @Field(key = "layout", name = "布局 VERTICAL | INLINE | HORIZONTAL", type = FieldTypeEnum.TEXT, defaultValue = "INLINE")
    private String layout;

    @Field(key = "column", name = "列数（1-100）", type = FieldTypeEnum.NUMBER, defaultValue = "1")
    private Integer column;

    @Field(key = "items", name = "表单项配置", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    private List<ItemConfig> items;

    public FromElementConfig() {
        super(SystemElementPathTool.buildJsPath("from"), SystemElementPathTool.buildCssPath("from"),
                SystemElementPathTool.buildTemplatePath("from"), FromElementTemplateConfig.class);
    }

    @Override
    protected void initConfig() {
        if (column == null) {
            column = 1;
        }
        if (layout == null) {
            layout = "HORIZONTAL";
        } else if (layout.toUpperCase().equals("INLINE")) {
            column = -1;
        }
    }

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    @SubConfig
    public static class ItemConfig {

        @Field(key = "id", name = "配置项ID（DOM元素ID）", type = FieldTypeEnum.TEXT)
        private String id;

        @Field(key = "fieldKey", name = "表单字段KEY", type = FieldTypeEnum.TEXT, isRequired = true)
        private String fieldKey;

        @Field(key = "fieldName", name = "表单字段名称", type = FieldTypeEnum.TEXT, isRequired = true)
        private String fieldName;

        @Field(key = "text", name = "text 和 element 配置任意一项即可", type = FieldTypeEnum.TEXT)
        private String text;

        @Field(key = "hide", name = "是否隐藏", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        private Boolean hide;

        @Field(key = "requiredFlag", name = "是否显示必填标识", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        private Boolean requiredFlag;

        @Field(key = "desc", name = "描述信息（或字段名）", type = FieldTypeEnum.TEXT)
        private String desc;

        @Field(key = "showDesc", name = "是否默认显示描述", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
        private Boolean showDesc;

        @Field(key = "error", name = "错误信息（或字段名）", type = FieldTypeEnum.TEXT)
        private String error;

        @Field(key = "showError", name = "是否默认显示错误信息", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        private Boolean showError;

        @Field(key = "element", name = "表单字段元素", type = FieldTypeEnum.ELEMENT)
        @FieldRef(key = PageSettingCanstant.ELEMENT_ENTITY_KEY)
        @SubElement
        private Object element;

        public String getId() {
            if (CheckEmptyUtil.isEmpty(id)) {
                id = UUID.randomUUID().toString();
            }
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Object getElement() {
            return element;
        }

        public void setElement(Object element) {
            this.element = element;
        }

        public String getFieldKey() {
            return fieldKey;
        }

        public void setFieldKey(String fieldKey) {
            this.fieldKey = fieldKey;
        }

        public Boolean getHide() {
            return hide;
        }

        public void setHide(Boolean hide) {
            this.hide = hide;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public Boolean getRequiredFlag() {
            return requiredFlag;
        }

        public void setRequiredFlag(Boolean requiredFlag) {
            this.requiredFlag = requiredFlag;
        }

        public Boolean getShowDesc() {
            return showDesc;
        }

        public void setShowDesc(Boolean showDesc) {
            this.showDesc = showDesc;
        }

        public Boolean getShowError() {
            return showError;
        }

        public void setShowError(Boolean showError) {
            this.showError = showError;
        }
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public List<ItemConfig> getItems() {
        return items;
    }

    public void setItems(List<ItemConfig> items) {
        this.items = items;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }
}
