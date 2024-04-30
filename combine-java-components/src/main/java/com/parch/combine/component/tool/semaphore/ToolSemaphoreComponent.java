package com.parch.combine.component.tool.semaphore;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.context.ComponentContextHandler;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.vo.DataResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * 限流组件
 */
public class ToolSemaphoreComponent extends AbsComponent<ToolSemaphoreInitConfig, ToolSemaphoreLogicConfig> {

    private final static Map<String, Semaphore> SEMAPHORE_MAP = new HashMap<>(16);

    /**
     * 构造器
     */
    public ToolSemaphoreComponent() {
        super(ToolSemaphoreInitConfig.class, ToolSemaphoreLogicConfig.class);
    }

    @Override
    public List<String> init() {
        ToolSemaphoreInitConfig initConfig = getInitConfig();
        if (initConfig.getMax() == null) {
            initConfig.setMax(20);
        }
        return new ArrayList<>();
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
        return CheckEmptyUtil.isEmpty(config.getKey()) ? ComponentContextHandler.getFlowKey() : config.getKey();
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
            Integer max = initConfig.getKeyMaxes() != null && initConfig.getKeyMaxes().get(key) != null ? initConfig.getKeyMaxes().get(key) : initConfig.getMax();
            semaphore = new Semaphore(max);
            SEMAPHORE_MAP.put(key, semaphore);
        }

        return semaphore;
    }
}
