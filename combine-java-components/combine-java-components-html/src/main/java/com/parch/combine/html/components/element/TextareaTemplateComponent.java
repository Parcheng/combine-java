package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.TextareaElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.textarea.register", order = 400, name = "多行文本框元素模板配置注册组件", logicConfigClass = TextareaElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TextareaTemplateComponent extends AbstractElementComponent<TextareaElementLogicConfig> {

    /**
     * 构造器
     */
    public TextareaTemplateComponent() {
        super(TextareaElementLogicConfig.class, "SYSTEM.TEXTAREA");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
