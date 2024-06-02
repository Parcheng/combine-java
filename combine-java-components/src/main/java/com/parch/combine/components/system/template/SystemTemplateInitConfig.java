package com.parch.combine.components.system.template;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.old.InitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 初始化配置类
 */
public class SystemTemplateInitConfig extends IInitConfig {

    @Field(key = "variableKey", name = "模板中自定义变量KEY", type = FieldTypeEnum.TEXT, defaultValue = "$config")
    @FieldDesc("逻辑配置的 configs 数据会初始化到流程中变量的该字段下")
    private String variableKey;

    @Field(key = "templates", name = "模板的组件配置集合", type = FieldTypeEnum.ANY, isArray = true, isRequired = true)
    private List<SystemTemplate> templates;

    @Override
    public void init() {
        if (CheckEmptyUtil.isEmpty(getVariableKey())) {
            setVariableKey("$config");
        }
    }

    public static class SystemTemplate {

        @Field(key = "key", name = "模板KEY", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
        private String key;

        @Field(key = "components", name = "模板包含的组件集合", type = FieldTypeEnum.COMPONENT, isArray = true, isRequired = true)
        private List<String> components;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<Object> getComponents() {
            return components;
        }

        public void setComponents(List<Object> components) {
            this.components = components;
        }
    }

    public List<SystemTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(List<SystemTemplate> templates) {
        this.templates = templates;
    }

    public String getVariableKey() {
        return variableKey;
    }

    public void setVariableKey(String variableKey) {
        this.variableKey = variableKey;
    }
}
