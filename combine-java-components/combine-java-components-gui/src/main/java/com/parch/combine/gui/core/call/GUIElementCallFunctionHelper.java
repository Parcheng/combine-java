package com.parch.combine.gui.core.call;

import com.parch.combine.gui.core.call.function.GUIAppendElementFunction;
import com.parch.combine.gui.core.call.function.GUICleanElementFunction;
import com.parch.combine.gui.core.call.function.GUIResetElementFunction;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.gui.core.element.GUIElementManagerHandler;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.JFrame;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

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
}
