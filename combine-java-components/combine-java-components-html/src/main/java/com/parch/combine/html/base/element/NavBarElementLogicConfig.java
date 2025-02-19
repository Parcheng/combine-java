package com.parch.combine.html.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;

public interface NavBarElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "brandText", name = "商标文本", type = FieldTypeEnum.TEXT)
        String brandText();

        @Field(key = "brandImg", name = "商标LOGO图片地址", type = FieldTypeEnum.TEXT)
        String brandImg();

        @Field(key = "defaultChecked", name = "默认选择项索引（从0开始）", type = FieldTypeEnum.NUMBER)
        Integer defaultChecked();

        @Field(key = "nav", name = "导航项配置", type = FieldTypeEnum.CONFIG, isRequired = true)
        @FieldObject(NavSettings.class)
        NavSettings nav();

        @Field(key = "defaultNavs", name = "导航默认项配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
        @FieldObject(NavData.class)
        NavData[] defaultNavs();

        @Field(key = "opts", name = "导航右栏操作配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
        @FieldObject(OptItemSettings.class)
        OptItemSettings[] opts();
    }

    interface OptItemSettings {

        @Field(key = "text", name = "按钮文本", type = FieldTypeEnum.TEXT)
        String text();

        @Field(key = "triggers", name = "按钮触发配置", type = FieldTypeEnum.COMPONENT, isArray = true)
        String[] triggers();
    }

    interface NavData {

        @Field(key = "text", name = "默认项文本", type = FieldTypeEnum.TEXT)
        String text();

        @Field(key = "index", name = "默认项位置索引（从0开始）", type = FieldTypeEnum.NUMBER)
        Integer index();

        @Field(key = "triggers", name = "默认项触发配置", type = FieldTypeEnum.COMPONENT, isArray = true)
        String[] triggers();
    }

    interface NavSettings {

        @Field(key = "text", name = "导航项文本", type = FieldTypeEnum.TEXT, isRequired = true)
        String text();

        @Field(key = "triggers", name = "导航项触发配置", type = FieldTypeEnum.COMPONENT, isArray = true)
        String[] triggers();
    }
}
