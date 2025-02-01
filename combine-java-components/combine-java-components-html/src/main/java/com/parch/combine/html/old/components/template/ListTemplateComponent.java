package com.parch.combine.html.old.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.old.base.template.ListElementTemplateLogicConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;

@Component(key = "template.list.register", order = 300, name = "列表元素模板配置注册组件", logicConfigClass = ListElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class ListTemplateComponent extends AbstractTemplateComponent<ListElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public ListTemplateComponent() {
        super(ListElementTemplateLogicConfig.class, "SYSTEM.LIST");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
