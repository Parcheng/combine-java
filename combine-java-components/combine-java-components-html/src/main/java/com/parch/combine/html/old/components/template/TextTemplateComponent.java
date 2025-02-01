package com.parch.combine.html.old.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.old.base.template.TextElementTemplateLogicConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;

@Component(key = "template.text.register", order = 300, name = "文本元素模板配置注册组件", logicConfigClass = TextElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TextTemplateComponent extends AbstractTemplateComponent<TextElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public TextTemplateComponent() {
        super(TextElementTemplateLogicConfig.class, "SYSTEM.TEXT");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
