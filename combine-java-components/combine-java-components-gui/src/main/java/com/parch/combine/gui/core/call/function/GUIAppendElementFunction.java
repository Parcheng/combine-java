package com.parch.combine.gui.core.call.function;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.gui.core.element.IGUIElement;

public class GUIAppendElementFunction extends AbstractGUIElementCallFunction{

    protected GUIElementManager guiElementManager;

    public GUIAppendElementFunction(String id, GUIElementManager guiElementManager, IGUIElement element) {
        super(id, "APPEND_ELEMENT", element);
        this.guiElementManager = guiElementManager;
    }

    @Override
    public Object execute(Object... params) {
        if (element.getContainer() == null || guiElementManager == null || CheckEmptyUtil.isEmpty(params)) {
            return false;
        }

        return addElements(params);
    }

    protected boolean addElements(Object... params) {
        boolean success = true;
        for (Object elementId : params) {
            if (elementId == null) {
                success = false;
                printError("elementId为空");
                continue;
            }

            IGUIElement addElement = guiElementManager.get(elementId.toString());
            if (element == null) {
                success = false;
                printError("ELEMENT " + elementId + " 未定义");
                continue;
            }

            try {
                element.getContainer().add(addElement.build(element.getFrame()));
            } catch (Exception e) {
                success = false;
                printError("添加元素异常: " + e.getMessage());
            }
        }

        if (success) {
            element.getContainer().repaint();
        }

        return success;
    }
}
