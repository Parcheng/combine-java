package com.parch.combine.tool.base.cache;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.vo.ComponentDataResult;

public abstract class AbstractCacheComponent<I extends IInitConfig, T extends CacheLogicConfig> extends AbstractComponent<I, T> {

    public AbstractCacheComponent(Class<I> initConfigClass, Class<T> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    public ComponentDataResult execute() {
        CacheLogicConfig logicConfig = getLogicConfig();

        String finalDomain = logicConfig.domain();
        if (finalDomain == null) {
            return ComponentDataResult.fail(CacheErrorEnum.DOMAIN_IS_NULL);
        }

        String finalKey = logicConfig.key();
        if (finalKey == null) {
            return ComponentDataResult.fail(CacheErrorEnum.KEY_IS_NULL);
        }

        return execute(finalDomain, finalKey);
    }

    protected abstract ComponentDataResult execute(String domain, String key);
}

