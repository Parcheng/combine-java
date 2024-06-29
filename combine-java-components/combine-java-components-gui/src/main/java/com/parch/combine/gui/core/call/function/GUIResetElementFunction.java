package com.parch.combine.gui.core.call.function;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.gui.core.element.IGUIElement;

public class GUIResetElementFunction extends GUIAppendElementFunction {

    public GUIResetElementFunction(String id, GUIElementManager guiElementManager, IGUIElement element) {
        super(id, guiElementManager, element);
        this.key = "RESET_ELEMENT";
    }

    @Override
    public Object execute(Object... params) {
        if (element.getContainer() == null || guiElementManager == null || CheckEmptyUtil.isEmpty(params)) {
            return false;
        }

        element.getContainer().removeAll();
        boolean success = super.addElements(params);
        if (success) {
            element.getContainer().repaint();
        }

        return success;
    }
}
