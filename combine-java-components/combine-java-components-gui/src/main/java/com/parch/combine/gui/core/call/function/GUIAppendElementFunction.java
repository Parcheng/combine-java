package com.parch.combine.gui.core.call.function;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.JFrame;
import java.awt.Container;

public class GUIAppendElementFunction extends AbstractGUIElementCallFunction{

    protected Container container;
    protected JFrame frame;
    protected GUIElementManager guiElementManager;

    public GUIAppendElementFunction(String id, GUIElementManager guiElementManager, Container container, JFrame frame) {
        super(id, "APPEND_ELEMENT");
        this.guiElementManager = guiElementManager;
        this.container = container;
        this.frame = frame;
    }

    @Override
    public Object execute(Object... params) {
        if (container == null || guiElementManager == null || CheckEmptyUtil.isEmpty(params)) {
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

            IGUIElement element = guiElementManager.get(elementId.toString());
            if (element == null) {
                success = false;
                printError("ELEMENT " + elementId + " 未定义");
                continue;
            }

            try {
                container.add(element.build(frame));
            } catch (Exception e) {
                success = false;
                printError("添加元素异常: " + e.getMessage());
            }
        }

        return success;
    }
}
