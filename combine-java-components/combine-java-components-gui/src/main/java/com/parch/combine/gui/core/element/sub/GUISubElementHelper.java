package com.parch.combine.gui.core.element.sub;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.GUIEventHandler;

import javax.swing.JComponent;
import java.util.HashMap;
import java.util.Map;

public class GUISubElementHelper {

    private GUISubElementHelper(){}

    public static GUISubElementConfig[] convert(GUIElementManager guiElementManager, SubElementLogicConfig[] elementConfigs) {
        if (elementConfigs == null) {
            return null;
        }

        GUISubElementConfig[] guiElements = new GUISubElementConfig[elementConfigs.length];
        for (int i = 0; i < elementConfigs.length; i++) {
            SubElementLogicConfig elementConfig = elementConfigs[i];
            guiElements[i] = new GUISubElementConfig();
            guiElements[i].dataField = elementConfig.dataField();
            guiElements[i].key = elementConfig.key() == null ? guiElements[i].dataField : elementConfig.key();
            guiElements[i].events = elementConfig.events();
            guiElements[i].element = guiElementManager.get(elementConfig.elementId());
            if (guiElements[i].element == null) {
                return null;
            }
        }

        return guiElements;
    }

    public static IGUIElement[] parse(GUIElementManager guiElementManager, String[] elementIds) {
        if (elementIds == null) {
            return null;
        }

        IGUIElement[] elements = new IGUIElement[elementIds.length];
        for (int i = 0; i < elementIds.length; i++) {
            String elementId = elementIds[i];
            elements[i] = guiElementManager.get(elementId);
            if (elements[i] == null) {
                return null;
            }
        }

        return elements;
    }

    public static JComponent[] build(Object data, GUISubElementConfig[] subElements, IGUIElement element) {
        JComponent[] body = new JComponent[subElements.length];
        for (int i = 0; i < subElements.length; i++) {
            GUISubElementConfig config = subElements[i];
            Object itemData = null;
            if (data != null) {
                if (CheckEmptyUtil.isEmpty(config.dataField)) {
                    itemData = data;
                } else if (data instanceof Map) {
                    itemData =  ((Map<?, ?>) data).get(config.dataField);
                }
            }

            config.element.setValue(itemData);
            body[i] = config.element.build(element.getFrame());
            GUIEventHandler.bindings(body[i], config.events, element);
        }

        return body;
    }

    @SuppressWarnings("unchecked")
    public static boolean setValue(Object data, GUISubElementConfig[] elements) {
        if (elements == null || elements.length == 0) {
            return false;
        }

        Map<String, Object> mapData = null;
        if (data instanceof Map) {
            mapData = (Map<String, Object>) data;
            for (GUISubElementConfig element : elements) {
                if (element.dataField == null) {
                    element.element.setValue(data);
                } else {
                    element.element.setValue(mapData.get(element.dataField));
                }
            }
        } else {
            for (GUISubElementConfig element : elements) {
                element.element.setValue(data);
            }
        }

        return true;
    }

    public static Object getValue(GUISubElementConfig[] elements) {
        if (elements == null || elements.length == 0) {
            return null;
        }

        Map<String, Object> data = new HashMap<>();
        for (GUISubElementConfig itemConfig : elements) {
            Object itemData = itemConfig.element.getData();
            if (itemConfig.key == null) {
                return itemData;
            }

            data.put(itemConfig.key, itemData);
        }

        return data;
    }
}
