package com.parch.combine.tool.base.event;

import java.util.HashMap;
import java.util.Map;

public class EventSubjectHandler {

    public static Map<String, EventObservable> SUBJECT_MAP = new HashMap<>(8);

    public static synchronized EventObservable register(String subject) {
        return SUBJECT_MAP.computeIfAbsent(subject, EventObservable::new);
    }

    public static boolean push(String subject, Map<String, Object> msg) {
        EventObservable eventObservable = SUBJECT_MAP.get(subject);
        if (eventObservable == null) {
            eventObservable = register(subject);
        }

        eventObservable.setChanged();
        eventObservable.notify(msg);
        return true;
    }

    public synchronized static void subscribe(String subject, IEventObserver observer) {
        EventObservable eventObservable = SUBJECT_MAP.get(subject);
        if (eventObservable == null) {
            eventObservable = register(subject);
        }

        eventObservable.addObserver(observer);
    }
}
