package com.parch.combine.gui.core.call;

import com.parch.combine.gui.core.call.function.GUIAddElementFunction;
import com.parch.combine.gui.core.call.function.GUICleanElementFunction;
import com.parch.combine.gui.core.call.function.GUISetElementFunction;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.gui.core.element.GUIElementManagerHandler;

import javax.swing.JFrame;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

public class GUIElementCallFunctionHelper {

    private GUIElementCallFunctionHelper() {}

    public static Map<String, IGUIElementCallFunction> buildElementFunction(String id, String domain, Container container, JFrame frame) {
        GUIElementManager guiElementManager = GUIElementManagerHandler.getManager(domain);
        Map<String, IGUIElementCallFunction> functionMap = new HashMap<>(8);

        GUIAddElementFunction addElement = new GUIAddElementFunction(id, guiElementManager, container, frame);
        functionMap.put(addElement.getKey(), addElement);

        GUISetElementFunction setElement = new GUISetElementFunction(id, guiElementManager, container, frame);
        functionMap.put(addElement.getKey(), setElement);

        GUICleanElementFunction cleanElement = new GUICleanElementFunction(id, container);
        functionMap.put(addElement.getKey(), cleanElement);

        return functionMap;
    }
}
