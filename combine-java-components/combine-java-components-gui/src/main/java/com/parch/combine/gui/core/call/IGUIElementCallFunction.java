package com.parch.combine.gui.core.call;

public interface IGUIElementCallFunction {

    String getKey();

    Object execute(Object... params);
}
