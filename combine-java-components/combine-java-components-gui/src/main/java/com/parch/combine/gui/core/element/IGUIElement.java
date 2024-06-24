package com.parch.combine.gui.core.element;

import javax.swing.*;
import java.util.Map;

public interface IGUIElement {

    JComponent build(JFrame frame);

    boolean setValue(Object data);

    Object getValue();

    Map<String, Object> getData();

    Object call(String key, Object ... params);

    IGUIElement copy();

    void setVisible(Boolean isVisible);
}
