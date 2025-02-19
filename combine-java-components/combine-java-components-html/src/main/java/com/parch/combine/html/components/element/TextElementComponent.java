package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.TextElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.text.register", order = 400, name = "文本元素模板配置注册组件", logicConfigClass = TextElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TextElementComponent extends AbstractElementComponent<TextElementLogicConfig> {

    /**
     * 构造器
     */
    public TextElementComponent() {
        super(TextElementLogicConfig.class, "SYSTEM.TEXT");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
