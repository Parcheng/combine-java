package com.parch.combine.component.call.flow;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.FlowKeyUtil;
import com.parch.combine.core.context.ComponentContext;
import com.parch.combine.core.context.ComponentContextHandler;
import com.parch.combine.component.call.CallComponent;
import com.parch.combine.core.handler.ComponentHandler;
import com.parch.combine.core.handler.FlowHandler;
import com.parch.combine.core.vo.DataResult;
import java.util.List;
import java.util.Map;

/**
 * 调用内部流程组件
 */
public class CallFlowComponent extends CallComponent<CallFlowInitConfig, CallFlowLogicConfig> {

    /**
     * 构造器
     */
    public CallFlowComponent() {
        super(CallFlowInitConfig.class, CallFlowLogicConfig.class);
    }

    @Override
    protected List<String> initCallConfig() {
        return null;
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
        List<String> componentIds = FlowHandler.list(domain, function);
        if (CheckEmptyUtil.isEmpty(componentIds)) {
            return DataResult.fail(CallFlowErrorEnum.FLOW_IS_NULL);
        }
        DataResult result = ComponentHandler.execute(url, params, headers, componentIds, null);

        // 还原上下文对象
        ComponentContextHandler.resetContext(context);

        return result;
    }
}
