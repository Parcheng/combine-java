package com.parch.combine.core.component.tools.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTool {

    private static final Map<String, ThreadPoolHandler> MAP = new HashMap<>(4);

    public static ExecutorService register(String scope, ThreadPoolConfig config) {
        return getHandler(scope).register(config);
    }

    public static ExecutorService getPool(String scope, ThreadPoolConfig config) {
        return getHandler(scope).getPool(config);
    }

    public static void closeAll(String scope) {
        getHandler(scope).clear();
    }

    public synchronized static void closeAll() {
        MAP.forEach((k, v) -> {
            if (v != null) {
                v.clear();
            }
        });
        MAP.clear();
    }

    public static ThreadPoolHandler getHandler(String scope) {
        ThreadPoolHandler handler = MAP.get(scope);
        if (handler != null) {
            return handler;
        }

        synchronized (MAP) {
            handler = MAP.get(scope);
            if (handler != null) {
                return handler;
            }

            handler = new ThreadPoolHandler();
            MAP.put(scope, handler);
            return handler;
        }
    }
}
