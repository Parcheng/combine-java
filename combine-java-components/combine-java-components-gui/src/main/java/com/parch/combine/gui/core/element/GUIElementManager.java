package com.parch.combine.gui.core.element;

import java.util.HashMap;
import java.util.Map;

public class GUIElementManager {

    private final Map<String, IGUIElement> elementMap = new HashMap<>(16);

    public void register(String id, IGUIElement element) {
        elementMap.put(id, element);
    }

    public IGUIElement get(String id) {
        return elementMap.get(id);
    }

    public boolean isExist(String id) {
        return elementMap.containsKey(id);
    }
}
