package com.parch.combine.tool.components;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.context.ComponentContext;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintHelper;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.tools.thread.ThreadPoolTool;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.tool.base.countdown.ToolCountDownLatchInitConfig;
import com.parch.combine.tool.base.countdown.ToolCountDownLatchLogicConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

@Component(key = "countdown", name = "CountDownLatch组件", logicConfigClass = ToolCountDownLatchLogicConfig.class, initConfigClass = ToolCountDownLatchInitConfig.class)
@ComponentResult(name = "主分支执行结果")
public class ToolCountDownLatchComponent extends AbstractComponent<ToolCountDownLatchInitConfig, ToolCountDownLatchLogicConfig> {

    private static final Map<String, ExecutorService> POOL_MAP = new HashMap<>(8);

    public ToolCountDownLatchComponent() {
        super(ToolCountDownLatchInitConfig.class, ToolCountDownLatchLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        ToolCountDownLatchLogicConfig logicConfig = getLogicConfig();

        ToolCountDownLatchLogicConfig.ItemConfig[] items = logicConfig.items();
        if (items != null && items.length > 0) {
            CountDownLatch latch = new CountDownLatch(items.length);
            ComponentContext context = ComponentContextHandler.getContext();

            // 子线程
            ExecutorService executor = ThreadPoolTool.getPool(getInitConfig().pool());
            for (ToolCountDownLatchLogicConfig.ItemConfig itemConfig : items) {
                if (CheckEmptyUtil.isEmpty(itemConfig.components())) {
                    latch.countDown();
                    continue;
                }

                executor.execute(() -> {
                    try {
                        ComponentContextHandler.setContext(context);
                        SubComponentTool.execute(manager, itemConfig.components());
                    } catch (Exception e) {
                        PrintHelper.printComponentError(e);
                    } finally {
                        ComponentContextHandler.clear();
                        latch.countDown();
                    }
                });
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                PrintHelper.printComponentError(e);
            }
        }

        ComponentDataResult result = SubComponentTool.execute(manager, logicConfig.components());
        return ComponentDataResult.success(result.getData());
    }
}
