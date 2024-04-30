package com.parch.combine.components.system.template;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.InitConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * 初始化配置类
 */
public class SystemTemplateInitConfig extends InitConfig {

    @ComponentField(key = "variableKey", name = "模板中自定义变量KEY", type = FieldTypeEnum.TEXT, defaultValue = "$config")
    @ComponentFieldDesc("逻辑配置的 configs 数据会初始化到流程中变量的该字段下")
    private String variableKey;

    @ComponentField(key = "templates", name = "模板的组件配置集合", type = FieldTypeEnum.OBJECT, isArray = true, isRequired = true)
    @ComponentFieldDesc("格式为键值对（ KEY:VALUE）; 其中KEY表示模板KEY, VALUE表示模板的组件配置集合")
    private Map<String, List<Map<String, Object>>> templates;

    @Override
    public void init() {
        if (CheckEmptyUtil.isEmpty(getVariableKey())) {
            setVariableKey("$config");
        }
    }

    public Map<String, List<Map<String, Object>>> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, List<Map<String, Object>>> templates) {
        this.templates = templates;
    }

    public String getVariableKey() {
        return variableKey;
    }

    public void setVariableKey(String variableKey) {
        this.variableKey = variableKey;
    }
}
