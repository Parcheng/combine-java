package com.parch.combine.core.component.tools.thread;

import com.parch.combine.core.component.manager.ResourceCloseManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolHandler implements ResourceCloseManager.IClear {

    private final Map<String, ExecutorService> POOL_MAP = new HashMap<>(8);

    public synchronized ExecutorService register(ThreadPoolConfig config) {
        String key = config.getKey();
        ExecutorService pool = POOL_MAP.get(key);
        if (pool == null) {
            pool = new ThreadPoolExecutor(config.getCorePoolSize(), config.getMaxPoolSize(),
                    config.getKeepAliveTime(), TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(config.getQueueCapacity()));
            POOL_MAP.put(key, pool);
        }

        return pool;
    }

    public ExecutorService getPool(ThreadPoolConfig config) {
        ExecutorService pool = POOL_MAP.get(config.getKey());
        if (pool == null) {
            return register(config);
        }

        return pool;
    }

    @Override
    public String getName() {
        return "ThreadPool";
    }

    @Override
    public synchronized boolean clear() {
        POOL_MAP.forEach((k, v) -> {
            if (v != null) {
                v.shutdown();
            }
        });
        POOL_MAP.clear();
        return true;
    }
}
