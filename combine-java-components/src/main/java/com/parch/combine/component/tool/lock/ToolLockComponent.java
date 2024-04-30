package com.parch.combine.component.tool.lock;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.vo.DataResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 限流组件
 */
public class ToolLockComponent extends AbsComponent<ToolLockInitConfig, ToolLockLogicConfig> {

    private final static Map<String, ReentrantLock> LOCK_MAP = new HashMap<>(16);

    /**
     * 构造器
     */
    public ToolLockComponent() {
        super(ToolLockInitConfig.class, ToolLockLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        ToolLockInitConfig initConfig = getInitConfig();
        if (initConfig.getAutoUnLock() == null) {
            initConfig.setAutoUnLock(true);
        }
        if (initConfig.getFair() == null) {
            initConfig.setFair(false);
        }

        ToolLockLogicConfig logicConfig = getLogicConfig();
        if (logicConfig.getCount() == null) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "加锁次数不能为空"));
        }

        return result;
    }

    @Override
    public DataResult execute() {
        ToolLockInitConfig initConfig = getInitConfig();
        ToolLockLogicConfig logicConfig = getLogicConfig();
        try {
            String key = getKey();
            ReentrantLock lock = getLock(key, initConfig);
            if (logicConfig.getCount() > 0) {
                lock(lock, Math.abs(logicConfig.getCount()));
            } else if (lock.isLocked()){
                unlock(lock, Math.abs(logicConfig.getCount()));
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
        if (initConfig.getAutoUnLock() && logicConfig.getCount() > 0) {
            String key = getKey();
            ReentrantLock lock = LOCK_MAP.get(key);
            if (lock != null) {
                unlock(lock, logicConfig.getCount());
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
        return CheckEmptyUtil.isEmpty(config.getKey()) ? "$DEFAULT" : config.getKey();
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
            lock = new ReentrantLock(initConfig.getFair());
            LOCK_MAP.put(key, lock);
        }

        return lock;
    }
}
