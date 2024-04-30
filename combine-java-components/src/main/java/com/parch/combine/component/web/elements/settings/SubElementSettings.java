package com.parch.combine.component.web.elements.settings;

import com.parch.combine.component.web.elements.entity.ElementEntity;

import java.util.List;

/**
 * 下级元素配置
 */
public class SubElementSettings {

    private String text;

    private List<ElementEntity<?>> elements;

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
