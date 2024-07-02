package com.parch.combine.gui.core.call.option;

import com.parch.combine.gui.base.build.GUIControlOptionConfig;
import java.util.Map;

public class GUIResetOptionFunction extends AbstractGUIOptionCallFunction {

    public GUIResetOptionFunction(IGUIOptionHandler handler) {
        super("RESET_OPTION", handler);
    }

    @Override
    public Object execute(Object... params) {
        if (params == null) {
            return false;
        }

        handler.cleanOptions();
        GUIControlOptionConfig[] newOptions = buildOptions(params);
        return handler.setOptions(newOptions);
    }
}
