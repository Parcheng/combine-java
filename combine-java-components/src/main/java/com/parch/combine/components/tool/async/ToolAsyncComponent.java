package com.parch.combine.components.tool.async;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.context.ComponentContext;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component(key = "async", name = "异步执行组件", logicConfigClass = ToolAsyncLogicConfig.class, initConfigClass = ToolAsyncInitConfig.class)
@ComponentResult(name = "true 已被提交执行成功")
public class ToolAsyncComponent extends AbsComponent<ToolAsyncInitConfig, ToolAsyncLogicConfig> {

    private static final Map<String, ExecutorService> POOL_MAP = new HashMap<>(8);

    public ToolAsyncComponent() {
        super(ToolAsyncInitConfig.class, ToolAsyncLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        ToolAsyncLogicConfig logicConfig = getLogicConfig();

        // 初始化逻辑中使用的组件
        List<Object> components = logicConfig.getComponents();
        if (CheckEmptyUtil.isNotEmpty(components)) {
            List<String> initErrors = SubComponentTool.init(manager, components);
            for (String initErrorMsg : initErrors) {
                result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, initErrorMsg));
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        ToolAsyncLogicConfig logicConfig = getLogicConfig();
        ExecutorService executor = getPool();
        executor.execute(new Task(manager, logicConfig.getComponents(), ComponentContextHandler.getContext()));
        return DataResult.success(true);
    }

    private ExecutorService getPool() {
        ToolAsyncInitConfig initConfig = getInitConfig();
        ExecutorService pool = POOL_MAP.get(initConfig.getId());
        if (pool != null) {
            return pool;
        }

        synchronized (POOL_MAP) {
            pool = POOL_MAP.get(initConfig.getId());
            if (pool == null) {
                pool = new ThreadPoolExecutor(initConfig.getCorePoolSize(), initConfig.getMaxPoolSize(),
                        initConfig.getKeepAliveTime(), TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(initConfig.getQueueCapacity()));
                POOL_MAP.put(initConfig.getId(), pool);
            }
        }

        return pool;
    }

    private static class Task implements Runnable {

        private List<Object> components;
        private ComponentContext context;
        private CombineManager combineManager;

        public Task(CombineManager combineManager, List<Object> components, ComponentContext context) {
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
