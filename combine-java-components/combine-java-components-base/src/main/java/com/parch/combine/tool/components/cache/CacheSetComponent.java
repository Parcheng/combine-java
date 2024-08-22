package com.parch.combine.tool.components.cache;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.tool.base.cache.AbstractCacheComponent;
import com.parch.combine.tool.base.cache.CacheHandler;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.tool.base.cache.set.CacheSetErrorEnum;
import com.parch.combine.tool.base.cache.set.CacheSetInitConfig;
import com.parch.combine.tool.base.cache.set.CacheSetLogicConfig;

@Component(key = "cache.set", order = 200, name = "设置缓存", logicConfigClass = CacheSetLogicConfig.class, initConfigClass = CacheSetInitConfig.class)
@ComponentResult(name = "异常信息或 true")
public class CacheSetComponent extends AbstractCacheComponent<CacheSetInitConfig, CacheSetLogicConfig> {

    public CacheSetComponent() {
        super(CacheSetInitConfig.class, CacheSetLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute(String domain, String key) {
        try {
            CacheSetInitConfig initConfig = getInitConfig();
            CacheSetLogicConfig logicConfig = getLogicConfig();

            Object finalValue = logicConfig.value();
            if (finalValue == null) {
                return ComponentDataResult.fail(CacheSetErrorEnum.VALUE_IS_NULL);
            }

            CacheHandler.set(domain, key, finalValue, logicConfig.expires(), initConfig.domainCapacity(), initConfig.keyCapacity());
        } catch (Exception e) {
            ComponentErrorHandler.print(CacheSetErrorEnum.FAIL, e);
            return ComponentDataResult.fail(CacheSetErrorEnum.FAIL);
        }

        return ComponentDataResult.success(true);
    }
}
