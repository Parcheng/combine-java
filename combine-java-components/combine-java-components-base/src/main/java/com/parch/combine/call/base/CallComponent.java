package com.parch.combine.call.base;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.vo.DataResult;

import java.util.Map;

public abstract class CallComponent<T extends IInitConfig, R extends CallLogicConfig> extends AbstractComponent<T, R> {

    public CallComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    public final DataResult execute() {
        CallLogicConfig logicConfig = getLogicConfig();
        return execute(logicConfig.url(), logicConfig.params(), logicConfig.headers());
    }

    /**
     * 执行
     *
     * @param url URL
     * @param params 参数
     * @return 结果
     */
    public abstract DataResult execute(String url, Map<String, Object> params, Map<String, String> headers);
}
