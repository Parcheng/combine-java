package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.base.template.TableElementTemplateLogicConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;

@Component(key = "template.table.register", order = 300, name = "表格元素模板配置注册组件", logicConfigClass = TableElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TableTemplateComponent extends AbstractTemplateComponent<TableElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public TableTemplateComponent() {
        super(TableElementTemplateLogicConfig.class, "SYSTEM.TABLE");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
