package com.parch.combine.core.ui.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public abstract class ElementConfig<T extends ElementTemplateConfig> {

    @Field(key = "id", name = "元素ID", type = FieldTypeEnum.TEXT, defaultValue = "随机字符粗")
    private String id;

    @Field(key = "type", name = "元素类型", type = FieldTypeEnum.SELECT, isRequired = true)
    private String type;

    @Field(key = "data", name = "初始数据", type = FieldTypeEnum.OBJECT)
    private Object data;

    @Field(key = "defaultData", name = "默认数据", type = FieldTypeEnum.OBJECT)
    private Object defaultData;

    @Field(key = "dataLoadId", name = "数据加载配置ID", type = FieldTypeEnum.TEXT)
    private String dataLoadId;

    @Field(key = "defaultLoad", name = "是否默认加载（构建元素时加载数据）", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    private Boolean defaultLoad;

    @Field(key = "refresh", name = "是否支持刷新", type = FieldTypeEnum.BOOLEAN)
    private Boolean refresh;

    @Field(key = "dataField", name = "数据二次取值的字段名", type = FieldTypeEnum.TEXT)
    private String dataField;

    @Field(key = "templateId", name = "引用模板ID", type = FieldTypeEnum.TEXT)
    private String templateId;

    private String elementJSPath;

    private Class<T> templateConfigClass;

    public ElementConfig(String elementJSPath, Class<T> templateConfigClass) {
        this.elementJSPath = elementJSPath;
        this.templateConfigClass = templateConfigClass;
    }

    public void init() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getDefaultData() {
        return defaultData;
    }

    public void setDefaultData(Object defaultData) {
        this.defaultData = defaultData;
    }

    public String getDataLoadId() {
        return dataLoadId;
    }

    public void setDataLoadId(String dataLoadId) {
        this.dataLoadId = dataLoadId;
    }

    public Boolean getDefaultLoad() {
        return defaultLoad;
    }

    public void setDefaultLoad(Boolean defaultLoad) {
        this.defaultLoad = defaultLoad;
    }

    public Boolean getRefresh() {
        return refresh;
    }

    public void setRefresh(Boolean refresh) {
        this.refresh = refresh;
    }

    public String getDataField() {
        return dataField;
    }

    public void setDataField(String dataField) {
        this.dataField = dataField;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Class<T> thisTemplateConfigClass() {
        return templateConfigClass;
    }

    public String getElementJSPath() {
        return elementJSPath;
    }

    public void setElementJSPath(String elementJSPath) {
        this.elementJSPath = elementJSPath;
    }
}
