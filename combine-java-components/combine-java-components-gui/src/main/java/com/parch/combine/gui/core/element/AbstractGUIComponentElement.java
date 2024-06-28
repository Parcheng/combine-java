package com.parch.combine.gui.core.element;

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.Map;

public abstract class AbstractGUIComponentElement<T, C extends GUIElementConfig<V>, V> extends AbstractGUIElement<T, C, V> {

    protected AbstractGUIComponentElement(String scopeKey, String domain, String id, Map<String, Object> data, String type, T template, C config, Class<T> templateClass) {
        super(scopeKey, domain, id, data, type, template, config, templateClass);
    }

    public final JComponent build(JFrame frame){
        this.frame = frame;
        JComponent component = build();
        this.container = component;
        this.container.setVisible(this.isVisible());
        return component;
    }

    protected abstract JComponent build();
}
