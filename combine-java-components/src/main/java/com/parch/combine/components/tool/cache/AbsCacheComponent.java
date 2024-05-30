package com.parch.combine.components.tool.cache;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.base.InitConfig;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.tools.variable.TextExpressionHelper;
import com.parch.combine.core.component.vo.DataResult;

import java.util.*;

public abstract class AbsCacheComponent<I extends InitConfig, T extends CacheLogicConfig> extends AbsComponent<I, T> {

    public AbsCacheComponent(Class<I> initConfigClass, Class<T> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    public List<String> init() {
        List<String> errorMsg = new ArrayList<>(1);
        CacheLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getDomain())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "缓存域为空"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getKey())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "缓存KEY为空"));
        }

        errorMsg.addAll(initConfig());
        return errorMsg;
    }

    protected abstract List<String> initConfig();

    @Override
    public DataResult execute() {
        CacheLogicConfig logicConfig = getLogicConfig();

        String finalDomain = TextExpressionHelper.getText(logicConfig.getDomain());
        if (finalDomain != null) {
            return DataResult.fail(CacheErrorEnum.DOMAIN_IS_NULL);
        }

        String finalKey = TextExpressionHelper.getText(logicConfig.getKey());
        if (finalKey == null) {
            return DataResult.fail(CacheErrorEnum.KEY_IS_NULL);
        }

        return execute(finalDomain, finalKey);
    }

    protected abstract DataResult execute(String domain, String key);
}

