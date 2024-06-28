package com.parch.combine.gui.core.element;

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.Map;

public interface IGUIElement {

    JComponent build(JFrame frame);

    boolean setValue(Object data);

    Object getValue();

    Object call(String key, Object ... params);

    IGUIElement copy();

    void setVisible(Boolean isVisible);

    boolean isVisible();

    String getScopeKey();

    String getDomain();

    JFrame getFrame();

    Map<String, Object> getData();
}
