package com.parch.combine.gui.core.element.sub;

import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;

public class GUISubElementConfig {
    public String key;
    public String dataField;
    public IGUIElement element;
    public EventConfig[] events;

    public static GUISubElementConfig copy(GUISubElementConfig source) {
        GUISubElementConfig newConfig = new GUISubElementConfig();
        newConfig.dataField = source.dataField;
        newConfig.key = source.key;
        newConfig.element = source.element.copy();
        newConfig.events = source.events;
        return newConfig;
    }
}
