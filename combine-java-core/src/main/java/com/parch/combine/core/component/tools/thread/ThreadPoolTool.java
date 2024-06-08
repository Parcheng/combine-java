package com.parch.combine.core.component.tools.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTool {

    private static final Map<String, ExecutorService> POOL_MAP = new HashMap<>(8);

    public synchronized static ExecutorService register(ThreadPoolConfig config) {
        String key = config.getKey();
        ExecutorService pool = POOL_MAP.get(key);
        if (pool == null) {
            pool = new ThreadPoolExecutor(config.getCorePoolSize(), config.getMaxPoolSize(),
                    config.getKeepAliveTime(), TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(config.getQueueCapacity()));
            POOL_MAP.put(key, pool);
        }

        return pool;
    }

    public static ExecutorService getPool(ThreadPoolConfig config) {
        ExecutorService pool = POOL_MAP.get(config.getKey());
        if (pool == null) {
            return register(config);
        }

        return pool;
    }
}
