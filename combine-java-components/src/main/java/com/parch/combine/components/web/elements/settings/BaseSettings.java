package com.parch.combine.components.web.elements.settings;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 页面元素设置
 */
public class BaseSettings {

    @Field(key = "dataField", name = "数据二次取值的字段名", type = FieldTypeEnum.TEXT)
    private String dataField;

    public String getDataField() {
        return dataField;
    }

    public void setDataField(String dataField) {
        this.dataField = dataField;
    }
}
