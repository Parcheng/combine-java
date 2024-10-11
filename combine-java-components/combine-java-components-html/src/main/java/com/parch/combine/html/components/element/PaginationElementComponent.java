package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.PaginationElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.pagination.register", order = 400, name = "分页元素模板配置注册组件", logicConfigClass = PaginationElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class PaginationElementComponent extends AbstractElementComponent<PaginationElementLogicConfig> {

    /**
     * 构造器
     */
    public PaginationElementComponent() {
        super(PaginationElementLogicConfig.class, "SYSTEM.PAGINATION");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}