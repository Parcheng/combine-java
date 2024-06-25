package com.parch.combine.gui.core.element;

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.Map;

public abstract class AbsComponentElement<T, C> extends AbsElement<T, C> {

    protected JComponent component;

    public AbsComponentElement(String scopeKey, String domain, String id, Map<String, Object> data, String type, T template, C config, Class<T> templateClass) {
        super(scopeKey, domain, id, data, type, template, config, templateClass);
    }

    public JComponent build(JFrame frame){
        this.frame = frame;
        this.component = build();
        return this.component;
    }

    protected abstract JComponent build();

    @Override
    public void setVisible(Boolean isVisible) {
        if (this.component == null || isVisible == null) {
            return;
        }

        this.component.setVisible(isVisible);
    }
}
