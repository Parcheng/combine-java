package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.base.template.ContentElementTemplateLogicConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;

@Component(key = "template.content.register", order = 300, name = "内容元素模板配置注册组件", logicConfigClass = ContentElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class ContentTemplateComponent extends AbstractTemplateComponent<ContentElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public ContentTemplateComponent() {
        super(ContentElementTemplateLogicConfig.class, "SYSTEM.CONTENT");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
