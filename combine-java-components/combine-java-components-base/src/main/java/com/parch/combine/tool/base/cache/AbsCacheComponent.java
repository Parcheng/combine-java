package com.parch.combine.tool.base.cache;

import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.vo.DataResult;

public abstract class AbsCacheComponent<I extends IInitConfig, T extends CacheLogicConfig> extends AbsComponent<I, T> {

    public AbsCacheComponent(Class<I> initConfigClass, Class<T> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    public DataResult execute() {
        CacheLogicConfig logicConfig = getLogicConfig();

        String finalDomain = logicConfig.domain();
        if (finalDomain != null) {
            return DataResult.fail(CacheErrorEnum.DOMAIN_IS_NULL);
        }

        String finalKey = logicConfig.key();
        if (finalKey == null) {
            return DataResult.fail(CacheErrorEnum.KEY_IS_NULL);
        }

        return execute(finalDomain, finalKey);
    }

    protected abstract DataResult execute(String domain, String key);
}

