package com.parch.combine.gui.core.element;

import java.util.HashMap;
import java.util.Map;

public class GUIElementManagerHandler {

    private GUIElementManagerHandler(){}

    private static Map<String, GUIElementManager> MANAGER_MAP;

    public static GUIElementManager getAndRegisterManager(String domain) {
        if (!check(domain)) {
            register(domain);
        }

        return getManager(domain);
    }

    public static boolean check(String domain) {
        return MANAGER_MAP != null && MANAGER_MAP.containsKey(domain);
    }

    public static GUIElementManager getManager(String domain) {
        return MANAGER_MAP == null ? null : MANAGER_MAP.get(domain);
    }

    public static synchronized void register(String domain) {
        if (MANAGER_MAP == null) {
            MANAGER_MAP = new HashMap<>(8);
        }

        if (MANAGER_MAP.containsKey(domain)) {
            return;
        }

        MANAGER_MAP.put(domain, new GUIElementManager());
    }
}
