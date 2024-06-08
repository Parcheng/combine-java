package com.parch.combine.components.tool.event.push;

import com.parch.combine.components.tool.event.EventSubjectHandler;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;

@Component(order = 100, key = "event.push", name = "事件消息推送组件", logicConfigClass = ToolEventPushLogicConfig.class, initConfigClass = ToolEventPushInitConfig.class)
@ComponentResult(name = "异常信息或 true 推送成功")
public class ToolEventPushComponent extends AbsComponent<ToolEventPushInitConfig, ToolEventPushLogicConfig> {

    public ToolEventPushComponent() {
        super(ToolEventPushInitConfig.class, ToolEventPushLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        try {
            ToolEventPushLogicConfig logicConfig = getLogicConfig();
            EventSubjectHandler.push(logicConfig.eventKey(), logicConfig.data());
        } catch (Exception e) {
            ComponentErrorHandler.print(ToolEventPushErrorEnum.FAIL, e);
            return DataResult.fail(ToolEventPushErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }
}
