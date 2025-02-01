package com.parch.combine.html.old.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.ConfigClearComponent;
import com.parch.combine.html.old.base.ConfigClearLogicConfig;
import com.parch.combine.html.common.cache.ElementTemplateConfigCache;

@Component(key = "template.clear", order = 399, name = "元素模板配置清除组件", logicConfigClass = ConfigClearLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TemplateClearComponent extends ConfigClearComponent {

    /**
     * 构造器
     */
    public TemplateClearComponent() {
        super(ElementTemplateConfigCache.INSTANCE);
    }
}
