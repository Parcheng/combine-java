package com.parch.combine.ui.elements.common;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.SubConfig;
import com.parch.combine.core.ui.base.element.SubElement;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

/**
 * 下级元素配置
 */
@SubConfig
public class SubElementConfig {

    @Field(key = "text", name = "文本内容", type = FieldTypeEnum.TEXT)
    private String text;

    @Field(key = "elements", name = "元素组配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = PageSettingCanstant.ELEMENT_ENTITY_KEY)
    @SubElement
    private Object elements;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getElements() {
        return elements;
    }

    public void setElements(Object elements) {
        this.elements = elements;
    }
}
