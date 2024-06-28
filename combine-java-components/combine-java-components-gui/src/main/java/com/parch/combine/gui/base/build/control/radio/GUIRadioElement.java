package com.parch.combine.gui.base.build.control.radio;

import com.parch.combine.gui.base.build.GUIControlOptionConfig;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JRadioButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import java.util.Map;

public class GUIRadioElement extends AbstractGUIComponentElement<GUIRadioElementTemplate, GUIRadioElement.Config, String> {

    private JRadioButton[] radios = null;

    public GUIRadioElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIRadioElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "radio", template, config, GUIRadioElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        ButtonGroup radioButton = new ButtonGroup();
        this.radios = new JRadioButton[this.config.options.length];
        for (int i = 0; i < this.config.options.length; i++) {
            GUIControlOptionConfig option = this.config.options[i];

            JRadioButton radioItem = new JRadioButton(option.getText() == null ? option.getValue() : option.getText(),
                    this.value != null && this.value.equals(option.getValue()));
            super.loadTemplates(radioItem, this.sysTemplate.getRadio(), this.template.getRadio());
            super.registerEvents(radioItem, this.config.events);

            panel.add(radioItem);
            radioButton.add(radioItem);
            this.radios[i] = radioItem;
        }

        return panel;
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        String dataStr = data.toString();
        for (int i = 0; i < this.config.options.length; i++) {
            GUIControlOptionConfig option = this.config.options[i];
            boolean selected = dataStr != null && dataStr.equals(option.getValue());
            if (this.radios != null) {
                this.radios[i].setSelected(selected);
            }
            if (selected) {
                this.value = option.getValue();
            }
        }

        return true;
    }

    @Override
    public Object getValue() {
        if (this.radios == null) {
            return this.value;
        }

        for (int i = 0; i < this.radios.length; i++) {
            if (this.radios[i].isSelected()) {
                return this.config.options[i].getValue();
            }
        }

        return null;
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIRadioElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public GUIControlOptionConfig[] options;
        public EventConfig[] events;
    }
}
