package com.parch.combine.components.web.elements;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.components.web.elements.entity.ElementEntity;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 逻辑配置类
 */
public class WebElementGroupLogicConfig extends LogicConfig {

    @Field(key = "elements", name = "页面元素配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = WebSettingCanstant.ELEMENT_ENTITY_KEY)
    private List<ElementEntity<?>> elements;

    public List<ElementEntity<?>> getElements() {
        return elements;
    }

    public void setElements(List<ElementEntity<?>> elements) {
        this.elements = elements;
    }
}
