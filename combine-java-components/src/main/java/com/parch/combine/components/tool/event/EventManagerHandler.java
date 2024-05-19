package com.parch.combine.components.tool.event;

import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventManagerHandler {

    public static Map<String, List<Consumer<Map<String, Object>>>> LISTENER_MAP = new HashMap<>(8);

    public static void notify(String eventKey, Map<String, Object> msg) {
        List<Consumer<Map<String, Object>>> list = LISTENER_MAP.get(eventKey);
        if (CheckEmptyUtil.isNotEmpty(list)) {
            for (Consumer<Map<String, Object>> item : list) {
                item.accept(msg);
            }
        }
    }

    public synchronized static void register(String eventKey, Consumer<Map<String, Object>> func) {
        List<Consumer<Map<String, Object>>> list = LISTENER_MAP.computeIfAbsent(eventKey, k -> new ArrayList<>());
        list.add(func);
    }
}
