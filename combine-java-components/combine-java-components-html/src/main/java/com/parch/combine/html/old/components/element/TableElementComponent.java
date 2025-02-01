package com.parch.combine.html.old.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.element.TableElementLogicConfig;
import com.parch.combine.html.old.base.element.core.AbstractElementComponent;
import com.parch.combine.html.old.base.element.core.ElementConfig;

@Component(key = "element.table.register", order = 400, name = "表格元素模板配置注册组件", logicConfigClass = TableElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TableElementComponent extends AbstractElementComponent<TableElementLogicConfig> {

    /**
     * 构造器
     */
    public TableElementComponent() {
        super(TableElementLogicConfig.class, "SYSTEM.TABLE");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
