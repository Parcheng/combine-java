package com.parch.combine.tool.components;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.tool.base.semaphore.ToolSemaphoreErrorEnum;
import com.parch.combine.tool.base.semaphore.ToolSemaphoreInitConfig;
import com.parch.combine.tool.base.semaphore.ToolSemaphoreLogicConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

@Component(key = "semaphore", name = "限流组件", logicConfigClass = ToolSemaphoreLogicConfig.class, initConfigClass = ToolSemaphoreInitConfig.class)
@ComponentResult(name = "true 或报错")
public class ToolSemaphoreComponent extends AbstractComponent<ToolSemaphoreInitConfig, ToolSemaphoreLogicConfig> {

    private final static Map<String, Semaphore> SEMAPHORE_MAP = new HashMap<>(16);

    /**
     * 构造器
     */
    public ToolSemaphoreComponent() {
        super(ToolSemaphoreInitConfig.class, ToolSemaphoreLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        try {
            String key = getKey();
            Semaphore semaphore = getSemaphore(key, getInitConfig());
            semaphore.acquire();
        } catch (InterruptedException e) {
            ComponentErrorHandler.print(ToolSemaphoreErrorEnum.FAIL, e);
            return DataResult.fail(ToolSemaphoreErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }

    @Override
    public boolean end() {
        String key = getKey();
        Semaphore semaphore = SEMAPHORE_MAP.get(key);
        if (semaphore != null) {
            semaphore.release();
        }

        return true;
    }

    /**
     * 获取KEY
     *
     * @return KEY
     */
    private String getKey() {
        ToolSemaphoreLogicConfig config = getLogicConfig();
        String key = config.key();
        return CheckEmptyUtil.isEmpty(key) ? ComponentContextHandler.getFlowKey() : key;
    }

    /**
     * 获取信号量对象
     *
     * @param key KEY
     * @param initConfig 初始化配置
     * @return 信号量对象
     */
    private synchronized static Semaphore getSemaphore(String key, ToolSemaphoreInitConfig initConfig) {
        Semaphore semaphore = SEMAPHORE_MAP.get(key);
        if (semaphore == null) {
            Map<String, Integer> keyMaxes = initConfig.keyMaxes();
            Integer max = keyMaxes != null && keyMaxes.get(key) != null ? keyMaxes.get(key) : initConfig.max();
            semaphore = new Semaphore(max);
            SEMAPHORE_MAP.put(key, semaphore);
        }

        return semaphore;
    }
}
