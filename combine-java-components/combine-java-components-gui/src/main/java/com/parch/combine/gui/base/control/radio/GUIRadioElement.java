package com.parch.combine.gui.base.control.radio;

import com.parch.combine.gui.base.control.GUIControlOptionConfig;
import com.parch.combine.gui.base.core.IGUIElement;
import com.parch.combine.gui.base.core.style.ElementHelper;
import com.parch.combine.gui.base.core.style.ElementStyleConstant;

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
        radios = new JRadioButton[config.options.length];
        for (int i = 0; i < config.options.length; i++) {
            GUIControlOptionConfig option = config.options[i];
            JRadioButton item = new JRadioButton(option.getText() == null ? option.getValue() : option.getText(),
                    config.value != null && config.value.equals(option.getValue()));
            panel.add(item);
            radioButton.add(item);
            radios[i] = item;
        }

        return panel;
    }

    @Override
    public boolean setData(Object data) {
        String dataStr = data.toString();
        for (int i = 0; i < config.options.length; i++) {
            GUIControlOptionConfig option = config.options[i];
            if (dataStr != null && dataStr.equals(option.getValue())) {
                radios[i].setSelected(true);
            } else {
                radios[i].setSelected(false);
            }
        }

        return true;
    }

    @Override
    public Object getData() {
        for (int i = 0; i < radios.length; i++) {
            if (radios[i].isSelected()) {
                return config.options[i].getValue();
            }
        }

        return null;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    public static class Config {
        public String value;
        public GUIControlOptionConfig[] options;
    }
}
