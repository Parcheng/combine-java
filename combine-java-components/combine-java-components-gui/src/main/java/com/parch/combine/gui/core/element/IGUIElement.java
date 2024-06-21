package com.parch.combine.gui.core.element;

import javax.swing.*;

public interface IGUIElement {

    JComponent build(JFrame frame);

    boolean setData(Object data);

    Object getData();

    Object call(String key, Object ... params);

    IGUIElement copy();
}
