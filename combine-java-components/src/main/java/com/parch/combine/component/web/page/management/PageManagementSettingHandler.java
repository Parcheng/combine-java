package com.parch.combine.component.web.page.management;

import com.parch.combine.component.web.page.WebPageSettingHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class PageManagementSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("page.management", "管理页面组件", true, PageManagementComponent.class);
        builder.addDesc("管理页面配置");
        WebPageSettingHandler.build(builder);

        builder.addProperty(PropertyTypeEnum.LOGIC, "tempPath", FieldTypeEnum.TEXT, "模板根路径",  false, false, "系统内置模板根路径");
        builder.addProperty(PropertyTypeEnum.LOGIC, "header", FieldTypeEnum.OBJECT, "页面头部元素配置",  false, false);
        WebPageSettingHandler.buildHtmlElement(builder, "header");
        builder.addProperty(PropertyTypeEnum.LOGIC, "footer", FieldTypeEnum.OBJECT, "页面底部元素配置",  false, false);
        WebPageSettingHandler.buildHtmlElement(builder, "footer");
        builder.addProperty(PropertyTypeEnum.LOGIC, "left", FieldTypeEnum.OBJECT, "页面左侧元素配置",  false, false);
        WebPageSettingHandler.buildHtmlElement(builder, "left");
        builder.addProperty(PropertyTypeEnum.LOGIC, "right", FieldTypeEnum.OBJECT, "页面右侧元素配置",  false, false);
        WebPageSettingHandler.buildHtmlElement(builder, "right");
        builder.addProperty(PropertyTypeEnum.LOGIC, "content", FieldTypeEnum.OBJECT, "页面内容（中间）元素配置",  false, false);
        WebPageSettingHandler.buildHtmlElement(builder, "content");


        builder.setResult("页面的HTML代码", false);
        return builder.get();
    }
}
