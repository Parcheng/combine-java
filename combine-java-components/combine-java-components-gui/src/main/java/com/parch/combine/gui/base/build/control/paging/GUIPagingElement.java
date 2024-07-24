package com.parch.combine.gui.base.build.control.paging;

import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.*;
import java.util.Map;

public class GUIPagingElement extends AbstractGUIComponentElement<GUIPagingElementTemplate, GUIPagingElement.Config, String> {

    private JLabel text = null;

    public GUIPagingElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIPagingElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "text", template, config, GUIPagingElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());

        this.text = new JLabel();
        this.text.setText(this.value);

        super.registerEvents(this.text, this.config.events);
        super.addSubComponent(panel, this.text, this.template.getText());

        return panel;
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        if (this.text != null) {
            this.text.setText(data.toString());
        }
        this.value = data.toString();

        return true;
    }

    @Override
    public Object getValue() {
        return this.text == null ? this.value : this.text.getText();
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIPagingElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public EventConfig[] events;
    }
}
