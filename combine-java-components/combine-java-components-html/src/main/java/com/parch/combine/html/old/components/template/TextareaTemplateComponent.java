package com.parch.combine.html.old.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.old.base.template.TextareaElementTemplateLogicConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;

@Component(key = "template.textarea.register", order = 300, name = "多行文本框元素模板配置注册组件", logicConfigClass = TextareaElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TextareaTemplateComponent extends AbstractTemplateComponent<TextareaElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public TextareaTemplateComponent() {
        super(TextareaElementTemplateLogicConfig.class, "SYSTEM.TEXTAREA");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
