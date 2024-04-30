package com.parch.combine.components.call.api;

import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.components.call.CallComponent;
import com.parch.combine.components.call.CallTypeEnum;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.tools.HttpHelper;
import com.parch.combine.core.vo.DataResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 调用内部流程组件
 */
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
    public List<String> initCallConfig() {
        List<String> errorMsg = new ArrayList<>();
        CallApiLogicConfig logicConfig = getLogicConfig();
        if (logicConfig.getTimeout() == null) {
            logicConfig.setTimeout(5000);
        }
        if (logicConfig.getRetry() == null) {
            logicConfig.setRetry(1);
        }

        CallTypeEnum mode = CallTypeEnum.get(logicConfig.getMode());
        if (mode == CallTypeEnum.NONE) {
            errorMsg.add("请求接口类型不合规，只支持 POST | GET");
        }

        return errorMsg;
    }

    @Override
    public DataResult execute(String url, Map<String, Object> params, Map<String, String> headers) {
        CallApiLogicConfig logicConfig = getLogicConfig();

        Object result = null;

        try {
            CallTypeEnum mode = CallTypeEnum.get(logicConfig.getMode());
            switch (mode) {
                case GET:
                    result = HttpHelper.doGet(url, params, headers, logicConfig.getRetry(), logicConfig.getTimeout());
                    break;
                case POST:
                    result = HttpHelper.doPost(url, JsonUtil.serialize(params), headers, logicConfig.getRetry(), logicConfig.getTimeout());
                    break;
                case FILE:
                    result = HttpHelper.downloadFile(url, params, headers, logicConfig.getRetry(), logicConfig.getTimeout());
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            ComponentErrorHandler.print(CallApiErrorEnum.FAIL, e);
            return DataResult.fail(CallApiErrorEnum.FAIL);
        }

        return DataResult.success(result);
    }
}
