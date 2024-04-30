package com.parch.combine.component.web.elements;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.component.web.elements.entity.ElementEntity;

import java.util.List;

/**
 * 逻辑配置类
 */
public class WebElementGroupLogicConfig extends LogicConfig {

    private List<ElementEntity<?>> elements;

    public List<ElementEntity<?>> getElements() {
        return elements;
    }

    public void setElements(List<ElementEntity<?>> elements) {
        this.elements = elements;
    }
}
