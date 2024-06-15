package com.parch.combine.components.call.flow;

import com.parch.combine.components.call.CallComponent;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.FlowKeyUtil;
import com.parch.combine.core.component.context.ComponentContext;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;

import java.util.List;
import java.util.Map;

/**
 * 调用内部流程组件
 */
@Component(key = "flow", name = "调用内部流程组件", logicConfigClass = CallFlowLogicConfig.class, initConfigClass = CallFlowInitConfig.class)
@ComponentResult(name = "调用流程的返回结果")
public class CallFlowComponent extends CallComponent<CallFlowInitConfig, CallFlowLogicConfig> {

    /**
     * 构造器
     */
    public CallFlowComponent() {
        super(CallFlowInitConfig.class, CallFlowLogicConfig.class);
    }

    @Override
    public DataResult execute(String url, Map<String, Object> params, Map<String, String> headers) {
        // 获取流程的KEY信息
        String[] keyArr = FlowKeyUtil.parseKey(url);
        String domain = keyArr[0];
        String function = keyArr[1];

        // 记录当前上下文对象
        ComponentContext context = ComponentContextHandler.getContext();

        // 执行流程
        List<String> componentIds = manager.getFlow().list(domain, function);
        if (CheckEmptyUtil.isEmpty(componentIds)) {
            return DataResult.fail(CallFlowErrorEnum.FLOW_IS_NULL);
        }
        DataResult result = manager.execute(url, params, headers, componentIds, null);

        // 还原上下文对象
        ComponentContextHandler.resetContext(context);

        return result;
    }
}
