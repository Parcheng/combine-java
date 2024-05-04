package com.parch.combine.page.elements;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.entity.ElementEntity;
import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 逻辑配置类
 */
public class WebElementGroupLogicConfig extends LogicConfig {

    @ComponentField(key = "elements", name = "页面元素配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @ComponentFieldRef(key = WebSettingCanstant.ELEMENT_ENTITY_KEY)
    private List<ElementEntity<?>> elements;

    public List<ElementEntity<?>> getElements() {
        return elements;
    }

    public void setElements(List<ElementEntity<?>> elements) {
        this.elements = elements;
    }
}
