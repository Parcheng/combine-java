package com.parch.combine.gui.core.element;

import java.util.HashMap;
import java.util.Map;

public class GUIElementManagerHandler {

    private GUIElementManagerHandler(){}

    private static Map<String, GUIElementManager> MANAGER_MAP;

    public static GUIElementManager getAndRegisterManager(String id) {
        if (!check(id)) {
            register(id);
        }

        return getManager(id);
    }

    public static boolean check(String id) {
        return MANAGER_MAP != null && MANAGER_MAP.containsKey(id);
    }

    public static GUIElementManager getManager(String id) {
        return MANAGER_MAP == null ? null : MANAGER_MAP.get(id);
    }

    public static synchronized void register(String id) {
        if (MANAGER_MAP == null) {
            MANAGER_MAP = new HashMap<>(8);
        }

        if (MANAGER_MAP.containsKey(id)) {
            return;
        }

        MANAGER_MAP.put(id, new GUIElementManager());
    }
}
