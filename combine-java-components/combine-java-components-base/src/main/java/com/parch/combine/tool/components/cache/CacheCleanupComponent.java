package com.parch.combine.tool.components.cache;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.tool.base.cache.AbstractCacheComponent;
import com.parch.combine.tool.base.cache.CacheData;
import com.parch.combine.tool.base.cache.CacheHandler;
import com.parch.combine.tool.base.cache.CacheKeyMatchRuleEnum;
import com.parch.combine.tool.base.cache.cleanup.CacheCleanupErrorEnum;
import com.parch.combine.tool.base.cache.cleanup.CacheCleanupInitConfig;
import com.parch.combine.tool.base.cache.cleanup.CacheCleanupLogicConfig;
import com.parch.combine.tool.base.cache.cleanup.CacheCleanupModeEnum;
import com.parch.combine.tool.base.cache.get.CacheGetErrorEnum;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component(key = "cache.cleanup", name = "缓存清理", logicConfigClass = CacheCleanupLogicConfig.class, initConfigClass = CacheCleanupInitConfig.class)
@ComponentResult(name = "被清理的缓存数据（KEY-VALUE键值对结构）")
public class CacheCleanupComponent extends AbstractCacheComponent<CacheCleanupInitConfig, CacheCleanupLogicConfig> {

    public CacheCleanupComponent() {
        super(CacheCleanupInitConfig.class, CacheCleanupLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute(String domain, String key) {
        try {
            CacheCleanupLogicConfig logicConfig = getLogicConfig();
            Map<String, Object> cleanupData = new HashMap<>();
            Map<String, CacheData> domainCache = CacheHandler.get(domain);
            if (domainCache == null) {
                return ComponentDataResult.success(cleanupData);
            }

            CacheKeyMatchRuleEnum keyMatchRule = CacheKeyMatchRuleEnum.get(logicConfig.keyMatchRule());
            if (keyMatchRule == CacheKeyMatchRuleEnum.NONE) {
                return ComponentDataResult.fail(CacheGetErrorEnum.KEY_MATCH_RULE_IS_ERROR);
            }

            Map<String, Object> cleanData = new HashMap<>();
            List<CacheData> dataList = CacheHandler.get(domain, key, keyMatchRule, false);
            Integer maxCount = logicConfig.maxCount();
            int loopCount = maxCount > 0 && maxCount < dataList.size() ? maxCount : dataList.size();

            CacheCleanupModeEnum mode = CacheCleanupModeEnum.get(logicConfig.mode());
            switch (mode) {
                case EXPIRED:
                    dataList = dataList.stream().filter(CacheHandler::isExpired).collect(Collectors.toList());
                    batchRemove(domain, dataList, cleanData, loopCount);
                    break;
                case NEAR_EXPIRED:
                    dataList = dataList.stream().filter(data -> !CacheHandler.isExpired(data))
                            .sorted(Comparator.comparingLong(CacheData::getCreateTime)).collect(Collectors.toList());
                    batchRemove(domain, dataList, cleanData, loopCount);
                    break;
                case ALL:
                    batchRemove(domain, dataList, cleanData, loopCount);
                    break;
                default:
                    return ComponentDataResult.fail(CacheCleanupErrorEnum.MODE_ERROR);
            }

            return ComponentDataResult.success(cleanData);
        } catch (Exception e) {
            ComponentErrorHandler.print(CacheCleanupErrorEnum.FAIL, e);
            return ComponentDataResult.fail(CacheCleanupErrorEnum.FAIL);
        }
    }

    public static void batchRemove(String domain, List<CacheData> dataList, Map<String, Object> cleanData, int loopCount) {
        for (int i = 0; i < loopCount; i++) {
            CacheData data = dataList.get(i);
            CacheHandler.remove(domain, data.getKey());
            cleanData.put(data.getKey(), data.getData());
        }
    }
}
