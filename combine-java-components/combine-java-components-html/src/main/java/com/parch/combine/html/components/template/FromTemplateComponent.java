package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.base.template.FromElementTemplateLogicConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;

@Component(key = "template.from.register", order = 300, name = "表单元素模板配置注册组件", logicConfigClass = FromElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class FromTemplateComponent extends AbstractTemplateComponent<FromElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public FromTemplateComponent() {
        super(FromElementTemplateLogicConfig.class, "SYSTEM.FROM");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
