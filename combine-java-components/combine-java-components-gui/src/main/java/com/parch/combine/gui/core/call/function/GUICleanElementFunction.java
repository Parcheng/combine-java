package com.parch.combine.gui.core.call.function;

import com.parch.combine.gui.core.element.IGUIElement;

import java.awt.Container;

public class GUICleanElementFunction extends AbstractGUIElementCallFunction{

    public GUICleanElementFunction(String id, IGUIElement element) {
        super(id, "CLEAN_ELEMENT", element);
    }

    @Override
    public Object execute(Object... params) {
        if (element.getContainer() == null) {
            return false;
        }

        element.getContainer().removeAll();
        element.getContainer().repaint();
        return true;
    }
}
