package com.parch.combine.gui.core.call;

import com.parch.combine.gui.core.call.element.GUIAppendElementFunction;
import com.parch.combine.gui.core.call.element.GUICleanElementFunction;
import com.parch.combine.gui.core.call.element.GUIResetElementFunction;
import com.parch.combine.gui.core.call.option.GUIAppendOptionFunction;
import com.parch.combine.gui.core.call.option.GUICleanOptionFunction;
import com.parch.combine.gui.core.call.option.GUIResetOptionFunction;
import com.parch.combine.gui.core.call.option.IGUIOptionHandler;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.gui.core.element.GUIElementManagerHandler;
import com.parch.combine.gui.core.element.IGUIElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class GUIElementCallFunctionHelper {

    private GUIElementCallFunctionHelper() {}

    public static Map<String, IGUIElementCallFunction> buildElementFunction(IGUIElement element) {
        GUIElementManager guiElementManager = GUIElementManagerHandler.getManager(element.getDomain());
        Map<String, IGUIElementCallFunction> functionMap = new HashMap<>(8);

        GUIAppendElementFunction addElement = new GUIAppendElementFunction(element.getId(), guiElementManager, element);
        functionMap.put(addElement.getKey(), addElement);

        GUIResetElementFunction setElement = new GUIResetElementFunction(element.getId(), guiElementManager, element);
        functionMap.put(setElement.getKey(), setElement);

        GUICleanElementFunction cleanElement = new GUICleanElementFunction(element.getId(), element);
        functionMap.put(cleanElement.getKey(), cleanElement);

        return functionMap;
    }

    public static Map<String, IGUIElementCallFunction> buildOptionFunction(IGUIOptionHandler handler) {
        Map<String, IGUIElementCallFunction> functionMap = new HashMap<>(8);

        GUIAppendOptionFunction addElement = new GUIAppendOptionFunction(handler);
        functionMap.put(addElement.getKey(), addElement);

        GUIResetOptionFunction setElement = new GUIResetOptionFunction(handler);
        functionMap.put(setElement.getKey(), setElement);

        GUICleanOptionFunction cleanElement = new GUICleanOptionFunction(handler);
        functionMap.put(cleanElement.getKey(), cleanElement);

        return functionMap;
    }
}
