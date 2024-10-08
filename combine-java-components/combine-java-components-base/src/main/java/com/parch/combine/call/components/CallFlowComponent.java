package com.parch.combine.call.components;

import com.parch.combine.call.base.CallComponent;
import com.parch.combine.call.base.flow.CallFlowErrorEnum;
import com.parch.combine.call.base.flow.CallFlowInitConfig;
import com.parch.combine.call.base.flow.CallFlowLogicConfig;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.FlowKeyUtil;
import com.parch.combine.core.component.context.ComponentContext;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.core.component.vo.FlowResult;

import java.util.List;
import java.util.Map;

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
    public ComponentDataResult execute(String url, Map<String, Object> params, Map<String, String> headers) {
        // 获取流程的KEY信息
        String[] keyArr = FlowKeyUtil.parseKey(url);
        String domain = keyArr[0];
        String function = keyArr[1];

        // 执行流程
        List<String> componentIds = manager.getFlow().list(domain, function);
        if (CheckEmptyUtil.isEmpty(componentIds)) {
            return ComponentDataResult.fail(CallFlowErrorEnum.FLOW_IS_NULL);
        }

        // 记录当前上下文对象
        ComponentContext context = ComponentContextHandler.getContext();

        FlowResult result = manager.execute(url, params, headers, componentIds, null);

        // 还原上下文对象
        ComponentContextHandler.resetContext(context);

        if (result.getSuccess()) {
            return ComponentDataResult.success(result.getData());
        } else {
            return ComponentDataResult.fail(result.getErrMsg(), result.getShowMsg());
        }
    }
}
