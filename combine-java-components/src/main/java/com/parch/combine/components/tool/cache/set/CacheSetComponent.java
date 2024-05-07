package com.parch.combine.components.tool.cache.set;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.components.tool.cache.CacheHandler;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;
import java.util.*;

/**
 * 组件设置信息组件
 */
@Component(key = "cache.set", name = "设置缓存", logicConfigClass = CacheSetLogicConfig.class, initConfigClass = CacheSetInitConfig.class)
@ComponentResult(name = "异常信息或 true")
public class CacheSetComponent extends AbsComponent<CacheSetInitConfig, CacheSetLogicConfig> {

    /**
     * 构造器
     */
    public CacheSetComponent() {
        super(CacheSetInitConfig.class, CacheSetLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> errorMsg = new ArrayList<>(1);
        CacheSetLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getDomain())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "缓存域为空"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getKey())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "缓存KEY为空"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getValue())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "缓存值为空"));
        }

        return errorMsg;
    }

    @Override
    public DataResult execute() {
        try {
            CacheSetInitConfig initConfig = getInitConfig();
            CacheSetLogicConfig logicConfig = getLogicConfig();

            Object finalKey = DataVariableHelper.parseValue(logicConfig.getKey(), false);
            if (finalKey == null) {
                return DataResult.fail(CacheSetErrorEnum.VALUE_IS_NULL);
            }

            Object finalValue = DataVariableHelper.parseValue(logicConfig.getValue(), false);
            if (finalValue == null) {
                return DataResult.fail(CacheSetErrorEnum.VALUE_IS_NULL);
            }

            CacheHandler.set(logicConfig.getDomain(), finalKey.toString(), finalValue,
                    logicConfig.getExpires(), initConfig.getDomainCapacity(), initConfig.getKeyCapacity());
        } catch (Exception e) {
            ComponentErrorHandler.print(CacheSetErrorEnum.FAIL, e);
            return DataResult.fail(CacheSetErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }
}
