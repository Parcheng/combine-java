package com.parch.combine.gui.base.core.manager;

import com.parch.combine.gui.base.core.IGUIElement;

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
}
