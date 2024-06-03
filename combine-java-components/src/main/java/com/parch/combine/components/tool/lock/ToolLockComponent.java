package com.parch.combine.components.tool.lock;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

import com.parch.combine.core.component.vo.DataResult;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Component(key = "lock", name = "锁组件", logicConfigClass = ToolLockLogicConfig.class, initConfigClass = ToolLockInitConfig.class)
@ComponentResult(name = "true 或报错")
public class ToolLockComponent extends AbsComponent<ToolLockInitConfig, ToolLockLogicConfig> {

    private final static Map<String, ReentrantLock> LOCK_MAP = new HashMap<>(16);

    public ToolLockComponent() {
        super(ToolLockInitConfig.class, ToolLockLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        ToolLockInitConfig initConfig = getInitConfig();
        ToolLockLogicConfig logicConfig = getLogicConfig();
        try {
            String key = getKey();
            ReentrantLock lock = getLock(key, initConfig);
            Integer count = logicConfig.count();
            if (count > 0) {
                lock(lock, Math.abs(count));
            } else if (lock.isLocked()){
                unlock(lock, Math.abs(count));
            }
        } catch (Exception e) {
            ComponentErrorHandler.print(ToolLockErrorEnum.FAIL, e);
            return DataResult.fail(ToolLockErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }

    @Override
    public boolean end() {
        ToolLockInitConfig initConfig = getInitConfig();
        ToolLockLogicConfig logicConfig = getLogicConfig();
        Integer count = logicConfig.count();
        if (initConfig.autoUnLock() && count > 0) {
            String key = getKey();
            ReentrantLock lock = LOCK_MAP.get(key);
            if (lock != null) {
                unlock(lock, count);
            }
        }

        return true;
    }

    /**
     * 加锁
     *
     * @param lock 锁对象
     * @param count 数量
     */
    private static void lock(ReentrantLock lock, int count) {
        for (int i = 0; i < count; i++) {
            lock.lock();
        }
    }

    /**
     * 解锁
     *
     * @param lock 锁对象
     * @param count 数量
     */
    private static void unlock(ReentrantLock lock, int count) {
        for (int i = 0; i < count; i++) {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    /**
     * 获取KEY
     *
     * @return KEY
     */
    private String getKey() {
        ToolLockLogicConfig config = getLogicConfig();
        String key = config.key();
        return CheckEmptyUtil.isEmpty(key) ? "$DEFAULT" : key;
    }

    /**
     * 获取锁对象
     *
     * @param key KEY
     * @param initConfig 初始化配置
     * @return 信号量对象
     */
    private synchronized static ReentrantLock getLock(String key, ToolLockInitConfig initConfig) {
        ReentrantLock lock = LOCK_MAP.get(key);
        if (lock == null) {
            lock = new ReentrantLock(initConfig.fair());
            LOCK_MAP.put(key, lock);
        }

        return lock;
    }
}
