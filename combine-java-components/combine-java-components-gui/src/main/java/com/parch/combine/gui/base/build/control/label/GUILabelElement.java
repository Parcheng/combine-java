package com.parch.combine.gui.base.build.control.label;

import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Map;

public class GUILabelElement extends AbstractGUIComponentElement<GUILabelElementTemplate, GUILabelElement.Config, String> {

    private JLabel label = null;

    public GUILabelElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUILabelElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "label", template, config, GUILabelElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());

        this.label = new JLabel();
        this.label.setText(this.value);

        super.registerEvents(this.label, this.config.events);
        super.addSubComponent(panel, this.label, this.template.getLabel());

        return panel;
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        if (this.label != null) {
            this.label.setText(data.toString());
        }
        this.value = data.toString();

        return true;
    }

    @Override
    public Object getValue() {
        return this.label == null ? this.value : this.label.getText();
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUILabelElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public EventConfig[] events;
    }
}
