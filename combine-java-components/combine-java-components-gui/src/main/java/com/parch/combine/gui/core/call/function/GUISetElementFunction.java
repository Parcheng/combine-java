package com.parch.combine.gui.core.call.function;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.GUIElementManager;
import javax.swing.JFrame;
import java.awt.Container;

public class GUISetElementFunction extends GUIAddElementFunction{

    public GUISetElementFunction(String id, GUIElementManager guiElementManager, Container container, JFrame frame) {
        super(id, guiElementManager, container, frame);
        this.key = "SET_ELEMENT";
    }

    @Override
    public Object execute(Object... params) {
        if (container == null || guiElementManager == null || CheckEmptyUtil.isEmpty(params)) {
            return false;
        }

        container.removeAll();
        return super.addElements(params);
    }
}
