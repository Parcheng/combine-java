package com.parch.combine.call.components;

import com.parch.combine.call.base.CallComponent;
import com.parch.combine.call.base.CallTypeEnum;
import com.parch.combine.call.base.api.CallApiErrorEnum;
import com.parch.combine.call.base.api.CallApiInitConfig;
import com.parch.combine.call.base.api.CallApiLogicConfig;
import com.parch.combine.core.common.util.HttpUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;

import java.io.IOException;
import java.util.Map;

@Component(key = "api", name = "调用外部接口组件", logicConfigClass = CallApiLogicConfig.class, initConfigClass = CallApiInitConfig.class)
@ComponentResult(name = "调用API返回的数据")
public class CallApiComponent extends CallComponent<CallApiInitConfig, CallApiLogicConfig> {

    /**
     * 构造器
     */
    public CallApiComponent() {
        super(CallApiInitConfig.class, CallApiLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute(String url, Map<String, Object> params, Map<String, String> headers) {
        CallApiLogicConfig logicConfig = getLogicConfig();

        Object result = null;

        try {
            CallTypeEnum mode = CallTypeEnum.get(logicConfig.mode());
            switch (mode) {
                case GET:
                    result = HttpUtil.doGet(url, params, headers, logicConfig.retry(), logicConfig.timeout());
                    break;
                case POST:
                    result = HttpUtil.doPost(url, JsonUtil.serialize(params), headers, logicConfig.retry(), logicConfig.timeout());
                    break;
                case FILE:
                    result = HttpUtil.downloadFile(url, params, headers, logicConfig.retry(), logicConfig.timeout());
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            ComponentErrorHandler.print(CallApiErrorEnum.FAIL, e);
            return ComponentDataResult.fail(CallApiErrorEnum.FAIL);
        }

        return ComponentDataResult.success(result);
    }
}
