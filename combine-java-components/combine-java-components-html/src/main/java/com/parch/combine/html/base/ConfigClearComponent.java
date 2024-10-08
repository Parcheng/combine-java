package com.parch.combine.html.base;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.vo.ComponentDataResult;

public abstract class ConfigClearComponent extends AbstractComponent<IInvalidInitConfig, ConfigClearLogicConfig> {

    protected IConfigClear clearHandler;

    /**
     * 构造器
     */
    public ConfigClearComponent(IConfigClear clearHandler) {
        super(IInvalidInitConfig.class, ConfigClearLogicConfig.class);
        this.clearHandler = clearHandler;
    }

    @Override
    protected ComponentDataResult execute() {
        ConfigClearLogicConfig logicConfig = getLogicConfig();
        String configId = logicConfig.configId();
        if (CheckEmptyUtil.isEmpty(configId)) {
            clearHandler.clear();
        } else {
            clearHandler.clear(configId);
        }

        return ComponentDataResult.success(true);
    }
}
