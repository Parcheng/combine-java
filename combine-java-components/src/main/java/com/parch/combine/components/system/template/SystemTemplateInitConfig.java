package com.parch.combine.components.system.template;

import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface SystemTemplateInitConfig extends IInitConfig {

    @Field(key = "variableKey", name = "模板中自定义变量KEY", type = FieldTypeEnum.TEXT, defaultValue = "$config")
    @FieldDesc("逻辑配置的 configs 数据会初始化到流程中变量的该字段下")
    default String variableKey() {
        return "$config";
    }

    @Field(key = "templates", name = "模板的组件配置集合", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(SystemTemplate.class)
    SystemTemplate[] templates();

    interface SystemTemplate {

        @Field(key = "key", name = "模板KEY", type = FieldTypeEnum.TEXT, isRequired = true)
        String key();

        @Field(key = "components", name = "模板包含的组件集合", type = FieldTypeEnum.COMPONENT, isArray = true, isRequired = true)
        String[] components();
    }
}
