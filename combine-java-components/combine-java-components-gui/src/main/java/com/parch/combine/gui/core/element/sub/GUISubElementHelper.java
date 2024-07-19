package com.parch.combine.gui.core.element.sub;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.GUIEventHandler;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.config.ElementGridConfig;
import com.parch.combine.gui.core.style.settings.ElementGridSettings;

import javax.swing.JComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static JComponent[] copyAndBuild(JComponent parent, Object data, GUISubElementConfig[] newSubElements, GUISubElementConfig[] sourceSubElements, IGUIElement element) {
        for (int i = 0; i < sourceSubElements.length; i++) {
            if (newSubElements.length == i) {
                break;
            }

            GUISubElementConfig configItem = sourceSubElements[i];
            newSubElements[i] = GUISubElementConfig.copy(configItem);
        }
        return GUISubElementHelper.build(parent, data, newSubElements, element);
    }

    public static JComponent[] build(JComponent parent, Object data, GUISubElementConfig[] subElements, IGUIElement element) {
        JComponent[] body = new JComponent[subElements.length];
        int rowCount = 1;
        for (int i = 0; i < subElements.length; i++) {
            GUISubElementConfig config = subElements[i];
            if (config == null) {
                continue;
            }

            Object itemData = null;
            if (data != null) {
                if (CheckEmptyUtil.isEmpty(config.dataField)) {
                    itemData = data;
                } else if (data instanceof Map) {
                    itemData = ((Map<?, ?>) data).get(config.dataField);
                }
            }

            config.element.setValue(itemData);
            body[i] = config.element.build(element.getFrame());
            GUIEventHandler.bindings(body[i], config.events, element);
            if (parent != null) {
                ElementHelper.addSubComponent(parent, body[i],
                        getGridConfig(element), new ElementGridSettings(1, rowCount++));
            }
        }

        return body;
    }

    private static ElementGridConfig getGridConfig(IGUIElement element) {
        return element.getTemplate() == null || element.getTemplate().getExternal() == null ?
                null : element.getTemplate().getExternal().getGrid();
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

    public static List<Object> getValueList(GUISubElementConfig[][] elements) {
        List<Object> data = new ArrayList<>();
        for (GUISubElementConfig[] item : elements) {
            data.add(getValue(item));
        }

        return data;
    }
}
