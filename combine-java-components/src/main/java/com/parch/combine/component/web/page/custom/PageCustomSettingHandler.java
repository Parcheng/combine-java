package com.parch.combine.component.web.page.custom;

import com.parch.combine.component.web.page.WebPageSettingHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class PageCustomSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("page.custom", "自定义页面组件", true, PageCustomComponent.class);
        builder.addDesc("自定义页面组件");
        WebPageSettingHandler.build(builder);

        builder.addProperty(PropertyTypeEnum.LOGIC, "tempPath", FieldTypeEnum.TEXT, "模板根路径",  true, false);

        builder.addProperty(PropertyTypeEnum.LOGIC, "configs", FieldTypeEnum.OBJECT, "页面元素配置集合",  true, true);
        builder.addProperty(PropertyTypeEnum.LOGIC, "configs.key", FieldTypeEnum.OBJECT, "配置KEY（对应模板中configs的KEY）",  true, true);
        builder.addProperty(PropertyTypeEnum.LOGIC, "configs.config", FieldTypeEnum.OBJECT, "配置内容",  true, true);
        WebPageSettingHandler.buildHtmlElement(builder, "configs.config");


        builder.setResult("页面的HTML代码", false);
        return builder.get();
    }
}
