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
        ElementHelper.set(panel, this.template.getExternal());

        this.comboBox = new JComboBox<>();
        ElementHelper.set(this.comboBox, this.template.getSelect());

        int checkIndex = -1;
        for (int i = 0; i < this.config.options.length; i++) {
            GUIControlOptionConfig option = this.config.options[i];
            this.comboBox.addItem(option.getText() == null ? option.getValue() : option.getText());
            if (checkIndex == -1 && this.config.value != null && this.config.value.equals(option.getValue())) {
                checkIndex = i;
            }
        }

        if (checkIndex != -1) {
            this.comboBox.setSelectedIndex(checkIndex);
        }

        panel.add(this.comboBox);
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
            if (dataStr != null && dataStr.equals(option.getValue())) {
                if (this.comboBox != null) {
                    this.comboBox.setSelectedIndex(i);
                }
                this.config.value = option.getValue();
                return true;
            }
        }

        return false;
    }

    @Override
    public Object getData() {
        if (this.comboBox == null) {
            return this.config.value;
        }

        int index =this.comboBox.getSelectedIndex();
        return index > 0 ? this.config.options[index].getValue() : null;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUISelectElement(this.template, this.config);
    }

    public static class Config {
        public String value;
        public GUIControlOptionConfig[] options;
    }
}
