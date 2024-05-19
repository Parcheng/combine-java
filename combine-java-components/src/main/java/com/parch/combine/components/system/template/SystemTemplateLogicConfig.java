package com.parch.combine.components.system.template;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * 逻辑配置类
 */
public class SystemTemplateLogicConfig extends LogicConfig {

    @Field(key = "key", name = "使用的模板KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("必须是初始化配置 configs 中存在的 KEY")
    private String key;

    @Field(key = "configs", name = "自定义变量配置对象", type = FieldTypeEnum.OBJECT)
    @FieldDesc("该数据会初始化到流程中变量, 变量 KEY 为初始化配置的 variableKey 指定")
    private Map<String, Object> configs;

    @FieldDesc("系统内部会自动赋值")
    private List<String> componentIds;

    @Override
    public void init() {}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> getConfigs() {
        return configs;
    }

    public void setConfigs(Map<String, Object> configs) {
        this.configs = configs;
    }

    public List<String> getComponentIds() {
        return componentIds;
    }

    public void setComponentIds(List<String> componentIds) {
        this.componentIds = componentIds;
    }
}
