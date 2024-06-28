package com.parch.combine.gui.core.element;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Window;
import java.util.Map;

public abstract class AbstractGUIWindowElement<T, C extends GUIElementConfig<V>, V> extends AbstractGUIElement<T, C, V> {

    protected AbstractGUIWindowElement(String scopeKey, String domain, String id, Map<String, Object> data, String type, T template, C config, Class<T> templateClass) {
        super(scopeKey, domain, id, data, type, template, config, templateClass);
    }

    public final JComponent build(JFrame frame){
        this.frame = frame;
        this.container = build();
        this.container.setVisible(this.isVisible());
        return null;
    }

    protected abstract Window build();
}
