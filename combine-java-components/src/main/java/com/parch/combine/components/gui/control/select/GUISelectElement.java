package com.parch.combine.components.gui.control.select;

import com.parch.combine.components.gui.control.GUIControlOptionConfig;
import com.parch.combine.components.gui.core.IGUIElement;
import com.parch.combine.components.gui.core.style.ElementHelper;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import javax.swing.*;
import java.awt.*;

public class GUISelectElement implements IGUIElement {

    private JComboBox<String> comboBox = null;

    private GUISelectElementTemplate template;
    private String value;
    private GUIControlOptionConfig[] options;

    public GUISelectElement(GUISelectElementTemplate template, String value, GUIControlOptionConfig[] options) {
        this.template = template == null ? new GUISelectElementTemplate() : template;
        this.value = value;
        this.options = options;
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ElementHelper.set(panel, template.getExternal());

        comboBox = new JComboBox<>();
        int checkIndex = -1;
        for (int i = 0; i < options.length; i++) {
            GUIControlOptionConfig option = options[i];
            comboBox.addItem(option.getText() == null ? option.getValue() : option.getText());
            if (checkIndex == -1 && value != null && value.equals(option.getValue())) {
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
    public boolean refresh(Object data) {
        if (comboBox == null) {
            return false;
        }

        if (data != null) {
            for (int i = 0; i < options.length; i++) {
                GUIControlOptionConfig option = options[i];
                if (value != null && value.equals(option.getValue())) {
                    comboBox.setSelectedIndex(i);
                    return true;
                }
            }

            return false;
        }

        return true;
    }

    @Override
    public Object getData() {
        return value;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }
}
