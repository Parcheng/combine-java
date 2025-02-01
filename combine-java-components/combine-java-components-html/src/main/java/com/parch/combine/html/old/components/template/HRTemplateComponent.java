package com.parch.combine.html.old.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.old.base.template.HRElementTemplateLogicConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;

@Component(key = "template.hr.register", order = 300, name = "分割线元素模板配置注册组件", logicConfigClass = HRElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class HRTemplateComponent extends AbstractTemplateComponent<HRElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public HRTemplateComponent() {
        super(HRElementTemplateLogicConfig.class, "SYSTEM.HR");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
