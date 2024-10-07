package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.AbstractTemplateComponent;
import com.parch.combine.html.base.template.SelectElementTemplateLogicConfig;
import com.parch.combine.html.base.template.ElementTemplateConfig;

@Component(key = "template.select.register", order = 300, name = "下拉选择元素模板配置注册组件", logicConfigClass = SelectElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class SelectTemplateComponent extends AbstractTemplateComponent<SelectElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public SelectTemplateComponent() {
        super(SelectElementTemplateLogicConfig.class, "SYSTEM.SELECT");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
