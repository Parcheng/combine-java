package com.parch.combine.gui.core.call.option;

public class GUICleanOptionFunction extends AbstractGUIOptionCallFunction {

    public GUICleanOptionFunction(IGUIOptionHandler handler) {
        super("CLEAN_OPTION", handler);
    }

    @Override
    public Object execute(Object... params) {
        return handler.cleanOptions();
    }
}
