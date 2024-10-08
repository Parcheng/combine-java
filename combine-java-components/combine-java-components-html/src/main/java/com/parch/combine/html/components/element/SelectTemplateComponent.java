package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.SelectElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.select.register", order = 400, name = "下拉选择元素模板配置注册组件", logicConfigClass = SelectElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class SelectTemplateComponent extends AbstractElementComponent<SelectElementLogicConfig> {

    /**
     * 构造器
     */
    public SelectTemplateComponent() {
        super(SelectElementLogicConfig.class, "SYSTEM.SELECT");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
