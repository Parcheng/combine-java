package com.parch.combine.gui.core.element;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Window;
import java.util.Map;

public abstract class AbsWindowGUIElement<T, C> extends AbsElement<T, C> {

    protected Window window;

    public AbsWindowGUIElement(String scopeKey, String domain, String id, Map<String, Object> data, String type, T template, C config, Class<T> templateClass) {
        super(scopeKey, domain, id, data, type, template, config, templateClass);
    }

    public JComponent build(JFrame frame){
        this.frame = frame;
        this.window = build();
        return null;
    }

    protected abstract Window build();

    @Override
    public void setVisible(Boolean isVisible) {
        if (this.window == null || isVisible == null) {
            return;
        }

        this.window.setVisible(isVisible);
    }
}
