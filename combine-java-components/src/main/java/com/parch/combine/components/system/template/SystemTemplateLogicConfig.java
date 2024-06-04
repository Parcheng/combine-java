package com.parch.combine.components.system.template;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.Map;

public interface SystemTemplateLogicConfig extends ILogicConfig {

    @Field(key = "key", name = "使用的模板KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("必须是初始化配置 configs 中存在的 KEY")
    String key();

    @Field(key = "configs", name = "自定义变量配置对象", type = FieldTypeEnum.MAP)
    @FieldDesc("该数据会初始化到流程中变量, 变量 KEY 为初始化配置的 variableKey 指定")
    Map<String, Object> configs();
}
