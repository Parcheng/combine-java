package com.parch.combine.tool.components.event;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.tool.base.event.EventSubjectHandler;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.tool.base.event.push.ToolEventPushErrorEnum;
import com.parch.combine.tool.base.event.push.ToolEventPushInitConfig;
import com.parch.combine.tool.base.event.push.ToolEventPushLogicConfig;

@Component(order = 100, key = "event.push", name = "事件消息推送组件", logicConfigClass = ToolEventPushLogicConfig.class, initConfigClass = ToolEventPushInitConfig.class)
@ComponentResult(name = "异常信息或 true 推送成功")
public class ToolEventPushComponent extends AbstractComponent<ToolEventPushInitConfig, ToolEventPushLogicConfig> {

    public ToolEventPushComponent() {
        super(ToolEventPushInitConfig.class, ToolEventPushLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        try {
            ToolEventPushLogicConfig logicConfig = getLogicConfig();
            EventSubjectHandler.push(logicConfig.eventKey(), logicConfig.data());
        } catch (Exception e) {
            ComponentErrorHandler.print(ToolEventPushErrorEnum.FAIL, e);
            return ComponentDataResult.fail(ToolEventPushErrorEnum.FAIL);
        }

        return ComponentDataResult.success(true);
    }
}
