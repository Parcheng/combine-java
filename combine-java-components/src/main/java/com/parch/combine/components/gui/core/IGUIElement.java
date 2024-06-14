package com.parch.combine.components.gui.core;

import javax.swing.*;

public interface IGUIElement {

    JComponent build();

    boolean setData(Object data);

    Object getData();

    Object call(String key, Object ... params);
}
