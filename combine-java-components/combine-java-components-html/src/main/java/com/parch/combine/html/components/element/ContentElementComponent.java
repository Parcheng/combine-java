package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.ContentElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.content.register", order = 400, name = "内容元素模板配置注册组件", logicConfigClass = ContentElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class ContentElementComponent extends AbstractElementComponent<ContentElementLogicConfig> {

    /**
     * 构造器
     */
    public ContentElementComponent() {
        super(ContentElementLogicConfig.class, "SYSTEM.CONTENT");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
