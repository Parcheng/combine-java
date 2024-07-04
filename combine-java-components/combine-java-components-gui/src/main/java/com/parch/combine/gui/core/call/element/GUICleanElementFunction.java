package com.parch.combine.gui.core.call.element;

import com.parch.combine.gui.core.element.IGUIElement;

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
