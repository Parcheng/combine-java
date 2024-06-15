package com.parch.combine.gui.base.control.select;

import com.parch.combine.gui.base.control.GUIControlOptionConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ElementStyleConstant;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class GUISelectElement implements IGUIElement {

    private JComboBox<String> comboBox = null;
    private GUISelectElementTemplate template;
    private Config config;

    public GUISelectElement(GUISelectElementTemplate template, Config config) {
        this.template = template == null ? new GUISelectElementTemplate() : template;
        this.config = config;
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ElementStyleConstant.LEFT_FLOW_LAYOUT);
        ElementHelper.set(panel, template.getExternal());

        comboBox = new JComboBox<>();
        int checkIndex = -1;
        for (int i = 0; i < config.options.length; i++) {
            GUIControlOptionConfig option = config.options[i];
            comboBox.addItem(option.getText() == null ? option.getValue() : option.getText());
            if (checkIndex == -1 && config.value != null && config.value.equals(option.getValue())) {
                checkIndex = i;
            }
        }
        if (checkIndex != -1) {
            comboBox.setSelectedIndex(checkIndex);
        }

        panel.add(comboBox);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (comboBox == null || data == null) {
            return false;
        }

        String dataStr = data.toString();
        for (int i = 0; i < config.options.length; i++) {
            GUIControlOptionConfig option = config.options[i];
            if (dataStr != null && dataStr.equals(option.getValue())) {
                comboBox.setSelectedIndex(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public Object getData() {
        int index = comboBox.getSelectedIndex();
        return index > 0 ? config.options[index].getValue() : null;
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
