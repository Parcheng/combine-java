package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.AbstractTemplateComponent;
import com.parch.combine.html.base.template.TitleElementTemplateLogicConfig;
import com.parch.combine.html.base.template.ElementTemplateConfig;

@Component(key = "template.title.register", order = 300, name = "标题元素模板配置注册组件", logicConfigClass = TitleElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TitleTemplateComponent extends AbstractTemplateComponent<TitleElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public TitleTemplateComponent() {
        super(TitleElementTemplateLogicConfig.class, "SYSTEM.TITLE");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
