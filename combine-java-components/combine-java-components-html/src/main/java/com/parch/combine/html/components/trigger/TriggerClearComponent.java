package com.parch.combine.html.components.trigger;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.ConfigClearComponent;
import com.parch.combine.html.base.ConfigClearLogicConfig;
import com.parch.combine.html.common.cache.TriggerConfigCache;

@Component(key = "trigger.clear", order = 299, name = "触发配置清除组件", logicConfigClass = ConfigClearLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TriggerClearComponent extends ConfigClearComponent {

    /**
     * 构造器
     */
    public TriggerClearComponent() {
        super(TriggerConfigCache.INSTANCE);
    }
}
