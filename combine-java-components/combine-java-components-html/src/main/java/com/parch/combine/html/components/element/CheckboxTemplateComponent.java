package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.CheckboxElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.checkbox.register", order = 400, name = "多选框元素模板配置注册组件", logicConfigClass = CheckboxElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class CheckboxTemplateComponent extends AbstractElementComponent<CheckboxElementLogicConfig> {

    /**
     * 构造器
     */
    public CheckboxTemplateComponent() {
        super(CheckboxElementLogicConfig.class, "SYSTEM.CHECKBOX");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
