package com.parch.combine.components.tool.cache.set;

import com.parch.combine.components.tool.cache.AbsCacheComponent;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.components.tool.cache.CacheHandler;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

import com.parch.combine.core.component.vo.DataResult;
import java.util.*;

@Component(key = "cache.set", name = "设置缓存", logicConfigClass = CacheSetLogicConfig.class, initConfigClass = CacheSetInitConfig.class)
@ComponentResult(name = "异常信息或 true")
public class CacheSetComponent extends AbsCacheComponent<CacheSetInitConfig, CacheSetLogicConfig> {

    public CacheSetComponent() {
        super(CacheSetInitConfig.class, CacheSetLogicConfig.class);
    }

    @Override
    public DataResult execute(String domain, String key) {
        try {
            CacheSetInitConfig initConfig = getInitConfig();
            CacheSetLogicConfig logicConfig = getLogicConfig();

            Object finalValue = logicConfig.value();
            if (finalValue == null) {
                return DataResult.fail(CacheSetErrorEnum.VALUE_IS_NULL);
            }

            CacheHandler.set(domain, key, finalValue, logicConfig.expires(), initConfig.domainCapacity(), initConfig.keyCapacity());
        } catch (Exception e) {
            ComponentErrorHandler.print(CacheSetErrorEnum.FAIL, e);
            return DataResult.fail(CacheSetErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }
}
