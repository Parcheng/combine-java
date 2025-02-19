package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.base.template.PaginationElementTemplateLogicConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;

@Component(key = "template.pagination.register", order = 300, name = "分页元素模板配置注册组件", logicConfigClass = PaginationElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class PaginationTemplateComponent extends AbstractTemplateComponent<PaginationElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public PaginationTemplateComponent() {
        super(PaginationElementTemplateLogicConfig.class, "SYSTEM.PAGINATION");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
