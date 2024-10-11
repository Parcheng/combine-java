package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.ListElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.list.register", order = 400, name = "列表元素模板配置注册组件", logicConfigClass = ListElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class ListElementComponent extends AbstractElementComponent<ListElementLogicConfig> {

    /**
     * 构造器
     */
    public ListElementComponent() {
        super(ListElementLogicConfig.class, "SYSTEM.LIST");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
