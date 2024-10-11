package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.FromElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.from.register", order = 400, name = "表单元素模板配置注册组件", logicConfigClass = FromElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class FromElementComponent extends AbstractElementComponent<FromElementLogicConfig> {

    /**
     * 构造器
     */
    public FromElementComponent() {
        super(FromElementLogicConfig.class, "SYSTEM.FROM");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
