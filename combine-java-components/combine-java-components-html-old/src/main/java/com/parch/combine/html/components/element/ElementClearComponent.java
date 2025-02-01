package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.ConfigClearComponent;
import com.parch.combine.html.base.ConfigClearLogicConfig;
import com.parch.combine.html.common.cache.ElementConfigCache;

@Component(key = "element.clear", order = 499, name = "元素配置清除组件", logicConfigClass = ConfigClearLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class ElementClearComponent extends ConfigClearComponent {

    /**
     * 构造器
     */
    public ElementClearComponent() {
        super(ElementConfigCache.INSTANCE);
    }
}
