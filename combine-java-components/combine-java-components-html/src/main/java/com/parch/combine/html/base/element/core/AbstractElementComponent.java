package com.parch.combine.html.base.element.core;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.html.base.ConfigErrorEnum;

public abstract class AbstractElementComponent<L extends ILogicConfig> extends AbstractComponent<IInvalidInitConfig, L> {

    protected String type;

    /**
     * 构造器
     */
    public AbstractElementComponent(Class<L> logicClass, String type) {
        super(IInvalidInitConfig.class, logicClass);
        this.type = type;
    }

    @Override
    protected ComponentDataResult execute() {
        L logicConfig = getLogicConfig();
        ElementConfig config = getConfig();
        if (config == null) {
            return ComponentDataResult.fail(ConfigErrorEnum.CONFIG_IS_NULL);
        }

        ElementConfigCache.INSTANCE.register(logicConfig.id(), type, config);
        return ComponentDataResult.success(true);
    }

    protected abstract ElementConfig getConfig();
}
