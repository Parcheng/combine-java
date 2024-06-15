package com.parch.combine.gui.base.control.radio;

import com.parch.combine.gui.base.control.GUIControlOptionConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ElementStyleConstant;

import javax.swing.JRadioButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;

public class GUIRadioElement implements IGUIElement {

    private JRadioButton[] radios = null;
    private GUIRadioElementTemplate template;
    private Config config;

    public GUIRadioElement(GUIRadioElementTemplate template, Config config) {
        this.template = template == null ? new GUIRadioElementTemplate() : template;
        this.config = config;
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ElementStyleConstant.LEFT_FLOW_LAYOUT);
        ElementHelper.set(panel, template.getExternal());

        ButtonGroup radioButton = new ButtonGroup();
        this.radios = new JRadioButton[this.config.options.length];
        for (int i = 0; i < this.config.options.length; i++) {
            GUIControlOptionConfig option = this.config.options[i];

            JRadioButton radioItem = new JRadioButton(option.getText() == null ? option.getValue() : option.getText(),
                    this.config.value != null && this.config.value.equals(option.getValue()));
            ElementHelper.set(radioItem, this.template.getRadio());

            panel.add(radioItem);
            radioButton.add(radioItem);
            this.radios[i] = radioItem;
        }

        return panel;
    }

    @Override
    public boolean setData(Object data) {
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
    public Object getData() {
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
        return new GUIRadioElement(this.template, this.config);
    }

    public static class Config {
        public String value;
        public GUIControlOptionConfig[] options;
    }
}
