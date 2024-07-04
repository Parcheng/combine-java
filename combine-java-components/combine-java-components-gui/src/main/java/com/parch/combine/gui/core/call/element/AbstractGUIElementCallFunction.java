package com.parch.combine.gui.core.call.element;


import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.IGUIElement;

public abstract class AbstractGUIElementCallFunction implements IGUIElementCallFunction {

    protected String id;
    protected String key;
    protected IGUIElement element;

    protected AbstractGUIElementCallFunction(String id, String key, IGUIElement element) {
        this.id = id;
        this.key = key.toUpperCase();
        this.element = element;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    protected void printError(String msg) {
        PrintUtil.printError("【GUI CALL FUNCTION】【" + id + "】【" + key + "】 ERROR: " + msg);
    }
}
