package com.parch.combine.component.web.elements;

import com.parch.combine.component.web.WebSettingHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class WebElementGroupSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("elements", "页面元素（组）配置组件", false, WebElementGroupComponent.class);
        // builder.addDesc("页面元素（组）配置");

        builder.addProperty(PropertyTypeEnum.LOGIC, "elements", FieldTypeEnum.OBJECT, "页面元素配置集合",  false, true);
        builder.addPropertyRef(PropertyTypeEnum.LOGIC, "elements", WebSettingHandler.ELEMENT_ENTITY_KEY, WebSettingHandler.ELEMENT_ENTITY_NAME);

        WebSettingHandler.buildEntity(builder);
        WebSettingHandler.buildDomConfig(builder);
        WebSettingHandler.buildTrigger(builder);
        WebSettingHandler.buildEntityDataLoad(builder);

        builder.setResult("页面元素（组）配置", false);
        return builder.get();
    }


}
