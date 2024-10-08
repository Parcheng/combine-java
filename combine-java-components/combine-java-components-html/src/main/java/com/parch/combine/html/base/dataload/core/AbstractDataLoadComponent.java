package com.parch.combine.html.base.dataload.core;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.html.base.ConfigErrorEnum;
import com.parch.combine.core.component.base.IInvalidInitConfig;

public abstract class AbstractDataLoadComponent<L extends ILogicConfig> extends AbstractComponent<IInvalidInitConfig, L> {

    protected DataLoadTypeEnum type;

    /**
     * 构造器
     */
    public AbstractDataLoadComponent(Class<L> logicClass, DataLoadTypeEnum type) {
        super(IInvalidInitConfig.class, logicClass);
        this.type = type;
    }

    @Override
    protected ComponentDataResult execute() {
        L logicConfig = getLogicConfig();
        DataLoadConfig config = getConfig();
        if (config == null) {
            return ComponentDataResult.fail(ConfigErrorEnum.CONFIG_IS_NULL);
        }

        DataloadConfigCache.INSTANCE.register(logicConfig.id(), type, config);
        return ComponentDataResult.success(true);
    }

    protected abstract DataLoadConfig getConfig();
}
