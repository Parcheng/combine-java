package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.base.template.CheckboxElementTemplateLogicConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;

@Component(key = "template.checkbox.register", order = 300, name = "多选框元素模板配置注册组件", logicConfigClass = CheckboxElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class CheckboxTemplateComponent extends AbstractTemplateComponent<CheckboxElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public CheckboxTemplateComponent() {
        super(CheckboxElementTemplateLogicConfig.class, "SYSTEM.CHECKBOX");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
