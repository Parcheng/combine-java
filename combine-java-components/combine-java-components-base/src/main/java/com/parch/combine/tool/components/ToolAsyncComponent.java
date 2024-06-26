package com.parch.combine.tool.components;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.context.ComponentContext;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.tools.thread.ThreadPoolTool;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.tool.base.async.ToolAsyncInitConfig;
import com.parch.combine.tool.base.async.ToolAsyncLogicConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Component(key = "async", name = "异步执行组件", logicConfigClass = ToolAsyncLogicConfig.class, initConfigClass = ToolAsyncInitConfig.class)
@ComponentResult(name = "true 已被提交执行成功")
public class ToolAsyncComponent extends AbstractComponent<ToolAsyncInitConfig, ToolAsyncLogicConfig> {

    private static final Map<String, ExecutorService> POOL_MAP = new HashMap<>(8);

    public ToolAsyncComponent() {
        super(ToolAsyncInitConfig.class, ToolAsyncLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        ToolAsyncLogicConfig logicConfig = getLogicConfig();
        ExecutorService executor = ThreadPoolTool.getPool(getInitConfig().pool());
        executor.execute(new Task(manager, logicConfig.components(), ComponentContextHandler.getContext()));
        return DataResult.success(true);
    }

    private static class Task implements Runnable {

        private String[] components;
        private ComponentContext context;
        private CombineManager combineManager;

        public Task(CombineManager combineManager, String[] components, ComponentContext context) {
            this.combineManager = combineManager;
            this.components = components;
            this.context = ComponentContext.copy(context);
        }

        @Override
        public void run() {
            ComponentContextHandler.setContext(context);
            SubComponentTool.execute(combineManager, components);
            ComponentContextHandler.clear();
        }
    }
}
