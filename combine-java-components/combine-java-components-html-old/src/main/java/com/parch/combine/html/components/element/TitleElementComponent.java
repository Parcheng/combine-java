package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.TitleElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.title.register", order = 400, name = "标题元素模板配置注册组件", logicConfigClass = TitleElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TitleElementComponent extends AbstractElementComponent<TitleElementLogicConfig> {

    /**
     * 构造器
     */
    public TitleElementComponent() {
        super(TitleElementLogicConfig.class, "SYSTEM.TITLE");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
