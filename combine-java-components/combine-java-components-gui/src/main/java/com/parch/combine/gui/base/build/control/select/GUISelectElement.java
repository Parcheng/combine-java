package com.parch.combine.gui.base.build.control.select;

import com.parch.combine.gui.base.build.GUIControlOptionConfig;
import com.parch.combine.gui.core.call.GUIElementCallFunctionHelper;
import com.parch.combine.gui.core.call.option.IGUIOptionHandler;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUISelectElement extends AbstractGUIComponentElement<GUISelectElementTemplate, GUISelectElement.Config, String> implements IGUIOptionHandler {

    private JComboBox<JComponent> comboBox = null;
    private List<GUIControlOptionConfig> options = null;

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

        this.setOptions(this.config.options);
        panel.add(this.comboBox);
        return panel;
    }

    @Override
    public boolean setOptions(GUIControlOptionConfig[] options) {
        if (this.comboBox == null || options == null) {
            return false;
        }
        this.cleanOptions();

        int checkIndex = -1;
        for (int i = 0; i < options.length; i++) {
            GUIControlOptionConfig option = options[i];
            if (option == null) {
                continue;
            }

            this.addOption(option);
            if (checkIndex == -1 && this.value != null && this.value.equals(option.getValue())) {
                checkIndex = i;
            }
        }

        if (checkIndex != -1) {
            this.comboBox.setSelectedIndex(checkIndex);
        }

        return true;
    }

    @Override
    public boolean addOption(GUIControlOptionConfig option) {
        if (this.comboBox == null) {
            return false;
        }

        JLabel text = new JLabel();
        text.setText(option.getText() == null ? option.getValue() : option.getText());
        this.comboBox.addItem(text);
        this.options.add(option);
        return true;
    }

    @Override
    public boolean cleanOptions() {
        if (this.comboBox == null) {
            return false;
        }

        this.options = new ArrayList<>();
        this.comboBox.removeAllItems();
        return true;
    }

    private ListCellRenderer<JComponent> getCellRenderer() {
        return (list, value, index, isSelected, cellHasFocus) -> {
            super.loadTemplates(value, this.sysTemplate.getOption(), this.template.getOption());
            return value;
        };
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null || this.options == null) {
            return false;
        }

        String dataStr = data.toString();
        for (int i = 0; i < this.options.size(); i++) {
            GUIControlOptionConfig option = this.options.get(i);
            if (dataStr != null && dataStr.equals(option.getValue())) {
                if (this.comboBox != null) {
                    this.comboBox.setSelectedIndex(i);
                }
                this.value = option.getValue();
                return true;
            }
        }

        return false;
    }

    @Override
    public Object getValue() {
        if (this.comboBox == null || this.options == null) {
            return this.value;
        }

        int index = this.comboBox.getSelectedIndex();
        return index > 0 ? this.options.get(index).getValue() : null;
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return GUIElementCallFunctionHelper.buildOptionFunction(this);
    }

    @Override
    public IGUIElement copy() {
        return new GUISelectElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public GUIControlOptionConfig[] options;
        public EventConfig[] events;
    }
}
