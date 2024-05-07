package com.parch.combine.ui.elements.settings;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.entity.ElementEntity;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 下级元素配置
 */
public class SubElementSettings {

    @Field(key = "text", name = "文本内容", type = FieldTypeEnum.TEXT)
    private String text;

    @Field(key = "elements", name = "元素组配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = WebSettingCanstant.ELEMENT_ENTITY_KEY)
    private List<ElementEntity<?>> elements;

    @Field(key = "elementsId", name = "引用元素组ID", type = FieldTypeEnum.TEXT)
    private String elementsId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ElementEntity<?>> getElements() {
        return elements;
    }

    public void setElements(List<ElementEntity<?>> elements) {
        this.elements = elements;
    }

    public String getElementsId() {
        return elementsId;
    }

    public void setElementsId(String elementsId) {
        this.elementsId = elementsId;
    }
}
