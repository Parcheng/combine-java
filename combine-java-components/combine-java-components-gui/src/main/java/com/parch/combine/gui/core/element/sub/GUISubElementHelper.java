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

    public static void setSubComponent(JComponent parent, GUISubElementConfig[] subElements) {
        if (parent == null) {
            return;
        }

        int rowCount = 1;
        for (GUISubElementConfig config : subElements) {
            if (config == null || config.buildResult == null) {
                continue;
            }

            ElementGridConfig gridConfig = config.element.getTemplate() == null
                    || config.element.getTemplate().getExternal() == null ?
                    null : config.element.getTemplate().getExternal().getGrid();
            ElementHelper.addSubComponent(parent, config.buildResult, gridConfig, new ElementGridSettings(1, rowCount++));
        }
    }

    public static GUISubElementConfig[] copyAndBuild(Object data, GUISubElementConfig[] sourceSubElements, IGUIElement element) {
        GUISubElementConfig[] newSubElements = new GUISubElementConfig[sourceSubElements.length];
        for (int i = 0; i < sourceSubElements.length; i++) {
            GUISubElementConfig configItem = sourceSubElements[i];
            newSubElements[i] = GUISubElementConfig.copy(configItem);
        }
        GUISubElementHelper.build(data, element, newSubElements);
        return newSubElements;
    }

    public static GUISubElementConfig copyAndBuild(Object data, GUISubElementConfig sourceSubElement, IGUIElement element) {
        GUISubElementConfig newSubElement = GUISubElementConfig.copy(sourceSubElement);
        GUISubElementHelper.build(data, element, newSubElement);
        return newSubElement;
    }

    private static void build(Object data, IGUIElement element, GUISubElementConfig ... subElements) {
        for (GUISubElementConfig config : subElements) {
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
            if (itemData == null) {
                itemData = config.defaultValue;
            }

            if (itemData == null && config.hasNullAssignment) {
                config.element.setValue(itemData);
            }
            config.buildResult = config.element.build(element.getFrame());
            GUIEventHandler.bindings(config.buildResult, config.events, element);
        }
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
                if (element == null) {
                    continue;
                }

                Object currData;
                if (element.dataField == null) {
                    currData = data;
                } else {
                    currData = mapData.get(element.dataField);
                }

                element.element.setValue(currData == null ? element.defaultValue : currData);
            }
        } else {
            for (GUISubElementConfig element : elements) {
                if (element == null) {
                    continue;
                }

                element.element.setValue(data == null ? element.defaultValue : data);
            }
        }

        return true;
    }

    public static Object getValue(GUISubElementConfig[] elements) {
        if (elements == null || elements.length == 0) {
            return null;
        }

        if (elements.length == 1 && elements[0] != null && elements[0].key == null) {
            Object data = elements[0].element.getData();
            return data == null ? elements[0].defaultValue : data;
        }

        Map<String, Object> data = new HashMap<>();
        for (GUISubElementConfig itemConfig : elements) {
            if (itemConfig == null || itemConfig.key == null) {
                continue;
            }

            Object itemData = itemConfig.element.getData();
            data.put(itemConfig.key, itemData == null ? itemConfig.defaultValue : itemData);
        }

        return data.isEmpty() ? null : data;
    }

    public static List<Object> batchGetValue(GUISubElementConfig[][] elements) {
        List<Object> data = new ArrayList<>();
        for (GUISubElementConfig[] item : elements) {
            data.add(getValue(item));
        }

        return data;
    }
}
