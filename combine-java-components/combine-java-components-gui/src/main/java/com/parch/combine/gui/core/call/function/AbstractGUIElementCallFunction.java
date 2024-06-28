package com.parch.combine.gui.core.call.function;


import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;

public abstract class AbstractGUIElementCallFunction implements IGUIElementCallFunction {

    protected String id;
    protected String key;

    protected AbstractGUIElementCallFunction(String id, String key) {
        this.id = id;
        this.key = key.toUpperCase();
    }

    @Override
    public String getKey() {
        return this.key;
    }

    protected void printError(String msg) {
        PrintUtil.printError("【GUI CALL FUNCTION】【" + id + "】【" + key + "】 ERROR: " + msg);
    }
}
