package com.parch.combine.gui.core.call.option;

import com.parch.combine.gui.base.build.GUIControlOptionConfig;

public class GUIAppendOptionFunction extends AbstractGUIOptionCallFunction {

    public GUIAppendOptionFunction(IGUIOptionHandler handler) {
        super("APPEND_OPTION", handler);
    }

    @Override
    public Object execute(Object... params) {
        if (params == null) {
            return false;
        }

        GUIControlOptionConfig[] options = buildOptions(params);
        for (GUIControlOptionConfig option : options) {
            handler.addOption(option);
        }

        return true;
    }


}
