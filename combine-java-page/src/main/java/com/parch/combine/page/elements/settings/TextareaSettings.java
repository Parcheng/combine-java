package com.parch.combine.page.elements.settings;

import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 页面元素设置
 */
public class TextareaSettings extends BaseSettings{

    @ComponentField(key = "key", name = "多行文本KEY属性，在获取数据时作为字段名", type = FieldTypeEnum.TEXT)
    private String key;

    @ComponentField(key = "value", name = "多行文本内容", type = FieldTypeEnum.TEXT)
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
