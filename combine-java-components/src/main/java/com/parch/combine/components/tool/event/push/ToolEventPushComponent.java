package com.parch.combine.components.tool.event.push;

import com.parch.combine.components.tool.event.EventManagerHandler;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

@Component(order = 100, key = "event.push", name = "事件消息推送组件", logicConfigClass = ToolEventPushLogicConfig.class, initConfigClass = ToolEventPushInitConfig.class)
@ComponentResult(name = "异常信息或 true 推送成功")
public class ToolEventPushComponent extends AbsComponent<ToolEventPushInitConfig, ToolEventPushLogicConfig> {

    public ToolEventPushComponent() {
        super(ToolEventPushInitConfig.class, ToolEventPushLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> errorMsg = new ArrayList<>(1);
        ToolEventPushLogicConfig logicConfig = getLogicConfig();
        if (logicConfig.getEventKey() == null) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "事件KEY为空"));
        }
        if (logicConfig.getData() == null) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "事件推送数据为空"));
        }

        return errorMsg;
    }

    @Override
    public DataResult execute() {
        try {
            ToolEventPushLogicConfig logicConfig = getLogicConfig();
            EventManagerHandler.notify(logicConfig.getEventKey(), logicConfig.getData());
        } catch (Exception e) {
            ComponentErrorHandler.print(ToolEventPushErrorEnum.FAIL, e);
            return DataResult.fail(ToolEventPushErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }
}
