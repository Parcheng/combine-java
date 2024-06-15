package com.parch.combine.components.tool.event.listener;

import com.parch.combine.components.tool.event.EventSubjectHandler;
import com.parch.combine.components.tool.event.IEventObserver;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.tools.thread.ThreadPoolConfig;
import com.parch.combine.core.component.tools.thread.ThreadPoolTool;
import com.parch.combine.core.component.vo.DataResult;

import java.util.Map;
import java.util.concurrent.ExecutorService;

@Component(order = 100, key = "event.listener", name = "注册事件监听组件", logicConfigClass = ToolEventListenerLogicConfig.class, initConfigClass = ToolEventListenerInitConfig.class)
@ComponentResult(name = "异常信息或 true 注册成功")
public class ToolEventListenerComponent extends AbsComponent<ToolEventListenerInitConfig, ToolEventListenerLogicConfig> {

    public ToolEventListenerComponent() {
        super(ToolEventListenerInitConfig.class, ToolEventListenerLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        try {
            ToolEventListenerLogicConfig logicConfig = getLogicConfig();
            EventSubjectHandler.subscribe(logicConfig.eventKey(),
                    new EventObserver(getInitConfig().pool(), logicConfig.components(), manager));
        } catch (Exception e) {
            ComponentErrorHandler.print(ToolEventListenerErrorEnum.FAIL, e);
            return DataResult.fail(ToolEventListenerErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }

    private static class EventObserver implements IEventObserver {
        private ThreadPoolConfig pool;
        private String[] components;
        private CombineManager combineManager;

        public EventObserver(ThreadPoolConfig pool, String[] components, CombineManager combineManager) {
            this.pool = pool;
            this.components = components;
            this.combineManager = combineManager;
        }

        @Override
        public ExecutorService getPool() {
            return ThreadPoolTool.getPool(pool);
        }

        @Override
        public void execute(String subject, String msgId, Map<String, Object> msg) {
            SubComponentTool.execute(combineManager, "Event-" + subject, msg, components);
        }
    }
}
