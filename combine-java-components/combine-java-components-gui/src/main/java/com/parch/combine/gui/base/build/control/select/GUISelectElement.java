package com.parch.combine.gui.base.build.control.select;

import com.parch.combine.gui.base.build.GUIControlOptionConfig;
import com.parch.combine.gui.core.element.AbsComponentElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.*;
import java.util.Map;

public class GUISelectElement extends AbsComponentElement<GUISelectElementTemplate, GUISelectElement.Config> {

    private JComboBox<JComponent> comboBox = null;

    public GUISelectElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUISelectElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "select", template, config, GUISelectElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.comboBox = new JComboBox<>();
        super.loadTemplates(this.comboBox, this.sysTemplate.getSelect(), this.template.getSelect());
        this.comboBox.setRenderer(getCellRenderer());
        super.registerEvents(this.comboBox, this.config.events);

        int checkIndex = -1;
        for (int i = 0; i < this.config.options.length; i++) {
            GUIControlOptionConfig option = this.config.options[i];

            JLabel text = new JLabel();
            text.setText(option.getText() == null ? option.getValue() : option.getText());
            this.comboBox.addItem(text);

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

    private ListCellRenderer<JComponent> getCellRenderer() {
        return (list, value, index, isSelected, cellHasFocus) -> {
            super.loadTemplates(value, this.sysTemplate.getOption(), this.template.getOption());
            return value;
        };
    }

    @Override
    public boolean setValue(Object data) {
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
    public Object getValue() {
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
        return new GUISelectElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config {
        public String value;
        public GUIControlOptionConfig[] options;
        public EventConfig[] events;
    }
}
