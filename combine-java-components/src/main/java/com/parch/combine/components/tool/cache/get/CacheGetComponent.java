package com.parch.combine.components.tool.cache.get;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.components.tool.cache.CacheData;
import com.parch.combine.components.tool.cache.CacheHandler;
import com.parch.combine.components.tool.cache.CacheKeyMatchRuleEnum;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 组件设置信息组件
 */
@Component(key = "cache.get", name = "获取缓存数据", logicConfigClass = CacheGetLogicConfig.class, initConfigClass = CacheGetInitConfig.class)
@ComponentResult(name = "缓存数据值")
public class CacheGetComponent extends AbsComponent<CacheGetInitConfig, CacheGetLogicConfig> {

    /**
     * 构造器
     */
    public CacheGetComponent() {
        super(CacheGetInitConfig.class, CacheGetLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> errorMsg = new ArrayList<>(1);
        CacheGetLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getDomain())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "缓存域为空"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getKey())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "缓存KEY为空"));
        }
        CacheKeyMatchRuleEnum keyMatchRule = CacheKeyMatchRuleEnum.get(logicConfig.getKeyMatchRule());
        if (keyMatchRule == CacheKeyMatchRuleEnum.NONE) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "KEY匹配策略不合规"));
        }

        return errorMsg;
    }

    @Override
    public DataResult execute() {
        try {
            CacheGetInitConfig initConfig = getInitConfig();
            CacheGetLogicConfig logicConfig = getLogicConfig();
            Object finalKey = DataVariableHelper.parseValue(logicConfig.getKey(), false);
            if (finalKey == null) {
                return DataResult.fail(CacheGetErrorEnum.KEY_IS_NULL);
            }

            CacheKeyMatchRuleEnum keyMatchRule = CacheKeyMatchRuleEnum.get(logicConfig.getKeyMatchRule());
            if (keyMatchRule == CacheKeyMatchRuleEnum.NONE) {
                return DataResult.fail(CacheGetErrorEnum.KEY_MATCH_RULE_IS_ERROR);
            }

            List<CacheData> results = CacheHandler.get(logicConfig.getDomain(), finalKey.toString(), keyMatchRule, initConfig.getRenewal());
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
