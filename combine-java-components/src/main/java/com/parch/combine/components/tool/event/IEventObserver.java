package com.parch.combine.components.tool.event;

import java.util.Map;
import java.util.concurrent.ExecutorService;

public interface IEventObserver {

    ExecutorService getPool();

    void execute(String subject, String msgId, Map<String, Object> msg);
}
