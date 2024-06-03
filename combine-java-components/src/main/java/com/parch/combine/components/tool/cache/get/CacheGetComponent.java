package com.parch.combine.components.tool.cache.get;

import com.parch.combine.components.tool.cache.AbsCacheComponent;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.components.tool.cache.CacheData;
import com.parch.combine.components.tool.cache.CacheHandler;
import com.parch.combine.components.tool.cache.CacheKeyMatchRuleEnum;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;


import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component(key = "cache.get", name = "获取缓存数据", logicConfigClass = CacheGetLogicConfig.class, initConfigClass = CacheGetInitConfig.class)
@ComponentResult(name = "缓存数据值")
public class CacheGetComponent extends AbsCacheComponent<CacheGetInitConfig, CacheGetLogicConfig> {

    public CacheGetComponent() {
        super(CacheGetInitConfig.class, CacheGetLogicConfig.class);
    }

    @Override
    public DataResult execute(String domain, String key) {
        try {
            CacheGetInitConfig initConfig = getInitConfig();
            CacheGetLogicConfig logicConfig = getLogicConfig();

            CacheKeyMatchRuleEnum keyMatchRule = CacheKeyMatchRuleEnum.get(logicConfig.keyMatchRule());
            if (keyMatchRule == CacheKeyMatchRuleEnum.NONE) {
                return DataResult.fail(CacheGetErrorEnum.KEY_MATCH_RULE_IS_ERROR);
            }

            List<CacheData> results = CacheHandler.get(domain, key, keyMatchRule, initConfig.renewal());
            if (results.isEmpty()) {
                return DataResult.success(results);
            }

            if (keyMatchRule == CacheKeyMatchRuleEnum.EXACT) {
                return DataResult.success(results.get(0).getData());
            } else {
                return DataResult.success(results.stream().map(CacheData::getData).collect(Collectors.toList()));
            }
        } catch (Exception e) {
            ComponentErrorHandler.print(CacheGetErrorEnum.FAIL, e);
            return DataResult.fail(CacheGetErrorEnum.FAIL);
        }
    }
}
