package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.TagElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.tag.register", order = 400, name = "标签元素模板配置注册组件", logicConfigClass = TagElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TagElementComponent extends AbstractElementComponent<TagElementLogicConfig> {

    /**
     * 构造器
     */
    public TagElementComponent() {
        super(TagElementLogicConfig.class, "SYSTEM.TAG");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
