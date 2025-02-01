package com.parch.combine.html.old.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.old.base.template.TagElementTemplateLogicConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;

@Component(key = "template.tag.register", order = 300, name = "标签元素模板配置注册组件", logicConfigClass = TagElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TagTemplateComponent extends AbstractTemplateComponent<TagElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public TagTemplateComponent() {
        super(TagElementTemplateLogicConfig.class, "SYSTEM.TAG");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
