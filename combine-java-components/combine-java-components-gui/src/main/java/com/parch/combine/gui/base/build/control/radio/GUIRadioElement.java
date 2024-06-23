package com.parch.combine.gui.base.build.control.radio;

import com.parch.combine.gui.base.build.GUIControlOptionConfig;
import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.JRadioButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import java.util.Map;

public class GUIRadioElement extends AbsGUIElement<GUIRadioElementTemplate, GUIRadioElement.Config> {

    private JRadioButton[] radios = null;

    public GUIRadioElement(String scopeKey, String elementId, Map<String, Object> data, GUIRadioElementTemplate template, Config config) {
        super(scopeKey, elementId, data, "radio", template, config, GUIRadioElementTemplate.class);
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
                    this.config.value != null && this.config.value.equals(option.getValue()));
            super.loadTemplates(radioItem, this.sysTemplate.getRadio(), this.template.getRadio());

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
                this.config.value = option.getValue();
            }
        }

        return true;
    }

    @Override
    public Object getValue() {
        for (int i = 0; i < this.radios.length; i++) {
            if (this.radios[i].isSelected()) {
                return this.config.options[i].getValue();
            }
        }

        return null;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIRadioElement(this.scopeKey, this.id, this.data, this.template, this.config);
    }

    public static class Config {
        public String value;
        public GUIControlOptionConfig[] options;
    }
}
