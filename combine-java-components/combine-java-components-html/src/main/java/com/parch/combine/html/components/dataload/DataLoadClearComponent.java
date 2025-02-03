package com.parch.combine.html.components.dataload;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.ConfigClearComponent;
import com.parch.combine.html.base.ConfigClearLogicConfig;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.html.common.cache.DataloadConfigCache;

@Component(key = "dataload.clear", order = 199, name = "加载配置清除组件", logicConfigClass = ConfigClearLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class DataLoadClearComponent extends ConfigClearComponent {

    /**
     * 构造器
     */
    public DataLoadClearComponent() {
        super(DataloadConfigCache.INSTANCE);
    }
}
