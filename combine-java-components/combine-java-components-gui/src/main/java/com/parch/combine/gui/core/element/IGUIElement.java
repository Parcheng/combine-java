package com.parch.combine.gui.core.element;

import com.parch.combine.gui.core.style.config.ElementGridConfig;

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Container;
import java.util.Map;

public interface IGUIElement {

    JComponent build(JFrame frame);

    boolean setValue(Object data);

    Object getValue();

    Object call(String key, Object ... params);

    IGUIElement copy();

    void setVisible(Boolean isVisible);

    boolean isVisible();

    String getId();

    Container getContainer();

    String getScopeKey();

    String getDomain();

    JFrame getFrame();

    <T extends BaseGUIElementTemplate> T getTemplate();

    Map<String, Object> getData();
}
