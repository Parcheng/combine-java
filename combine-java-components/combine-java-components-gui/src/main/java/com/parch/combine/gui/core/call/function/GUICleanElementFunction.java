package com.parch.combine.gui.core.call.function;

import java.awt.Container;

public class GUICleanElementFunction extends AbstractGUIElementCallFunction{

    protected Container container;

    public GUICleanElementFunction(String id, Container container) {
        super(id, "CLEAN_ELEMENT");
        this.container = container;
    }

    @Override
    public Object execute(Object... params) {
        if (container == null) {
            return false;
        }

        container.removeAll();
        return true;
    }
}
