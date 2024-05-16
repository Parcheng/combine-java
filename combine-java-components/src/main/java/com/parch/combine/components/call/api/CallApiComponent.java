package com.parch.combine.components.call.api;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.components.call.CallComponent;
import com.parch.combine.components.call.CallTypeEnum;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.HttpTool;
import com.parch.combine.core.component.vo.DataResult;

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
                    result = HttpTool.doGet(url, params, headers, logicConfig.getRetry(), logicConfig.getTimeout());
                    break;
                case POST:
                    result = HttpTool.doPost(url, JsonUtil.serialize(params), headers, logicConfig.getRetry(), logicConfig.getTimeout());
                    break;
                case FILE:
                    result = HttpTool.downloadFile(url, params, headers, logicConfig.getRetry(), logicConfig.getTimeout());
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
