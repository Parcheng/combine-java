package com.parch.combine.component.system.template;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * 设置处理器
 */
public class SystemTemplateSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("template", "模板组件", false, SystemTemplateComponent.class);
        // builder.addDesc("模板组件");

        builder.addProperty(PropertyTypeEnum.INIT, "variableKey", FieldTypeEnum.TEXT, "模板配置的变量名称",  false, false, "$config");
        builder.addProperty(PropertyTypeEnum.INIT, "templates", FieldTypeEnum.OBJECT, "模板配置",  true, false);
        builder.addPropertyDesc(PropertyTypeEnum.INIT, "templates", "格式为键值对（ KEY:VALUE）; 其中KEY表示模板KEY, VALUE表示模板的组件配置集合");


        builder.addProperty(PropertyTypeEnum.LOGIC, "key", FieldTypeEnum.TEXT, "使用的模板KEY",  true, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "configs", FieldTypeEnum.OBJECT, "自定义变量集合",  true, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "configs", "格式为键值对（ KEY : VALUE）; 其中KEY表示变量名, VALUE表示变量值");


        builder.setResult("模板中组件报错信息或 true", false);
        return builder.get();
    }
}
