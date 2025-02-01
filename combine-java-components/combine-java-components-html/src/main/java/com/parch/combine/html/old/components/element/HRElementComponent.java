package com.parch.combine.html.old.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.element.HRElementLogicConfig;
import com.parch.combine.html.old.base.element.core.AbstractElementComponent;
import com.parch.combine.html.old.base.element.core.ElementConfig;

@Component(key = "element.hr.register", order = 400, name = "分割线元素模板配置注册组件", logicConfigClass = HRElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class HRElementComponent extends AbstractElementComponent<HRElementLogicConfig> {

    /**
     * 构造器
     */
    public HRElementComponent() {
        super(HRElementLogicConfig.class, "SYSTEM.HR");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
