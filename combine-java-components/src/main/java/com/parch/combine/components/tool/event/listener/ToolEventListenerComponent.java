package com.parch.combine.components.tool.event.listener;

import com.parch.combine.components.tool.event.EventSubjectHandler;
import com.parch.combine.components.tool.event.IEventObserver;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.tools.thread.ThreadPoolTool;
import com.parch.combine.core.component.vo.DataResult;

import java.util.*;
import java.util.concurrent.ExecutorService;

@Component(order = 100, key = "event.listener", name = "注册事件监听组件", logicConfigClass = ToolEventListenerLogicConfig.class, initConfigClass = ToolEventListenerInitConfig.class)
@ComponentResult(name = "异常信息或 true 注册成功")
public class ToolEventListenerComponent extends AbsComponent<ToolEventListenerInitConfig, ToolEventListenerLogicConfig> {

    public ToolEventListenerComponent() {
        super(ToolEventListenerInitConfig.class, ToolEventListenerLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> errorMsg = new ArrayList<>(1);
        ToolEventListenerLogicConfig logicConfig = getLogicConfig();
        if (logicConfig.getEventKey() == null) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "事件KEY为空"));
        }

        // 初始化逻辑中使用的组件
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getComponents())) {
            List<String> initErrorMsgs = SubComponentTool.init(manager, logicConfig.getComponents());
            for (String initErrorMsg : initErrorMsgs) {
                errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, initErrorMsg));
            }
        }

        return errorMsg;
    }

    @Override
    public DataResult execute() {
        try {
            ToolEventListenerLogicConfig logicConfig = getLogicConfig();
            EventSubjectHandler.subscribe(logicConfig.getEventKey(),
                    new EventObserver(getInitConfig().getPool().getKey(), logicConfig.getComponents(), manager));
        } catch (Exception e) {
            ComponentErrorHandler.print(ToolEventListenerErrorEnum.FAIL, e);
            return DataResult.fail(ToolEventListenerErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }

    private static class EventObserver implements IEventObserver {
        private String poolKey;
        private List<Object> components;
        private CombineManager combineManager;

        public EventObserver(String poolKey, List<Object> components, CombineManager combineManager) {
            this.poolKey = poolKey;
            this.components = components;
            this.combineManager = combineManager;
        }

        @Override
        public ExecutorService getPool() {
            return ThreadPoolTool.getPool(poolKey);
        }

        @Override
        public void execute(String subject, String msgId, Map<String, Object> msg) {
            SubComponentTool.execute(combineManager, "Event-" + subject, msg, components);
        }
    }
}
