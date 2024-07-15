package com.parch.combine.gui.base.build.control.checkbox;

import com.parch.combine.gui.base.build.GUIControlOptionConfig;
import com.parch.combine.gui.core.call.GUIElementCallFunctionHelper;
import com.parch.combine.gui.core.call.option.IGUIOptionHandler;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GUICheckboxElement extends AbstractGUIComponentElement<GUICheckboxElementTemplate, GUICheckboxElement.Config, String[]> implements IGUIOptionHandler {

    private JPanel panel = null;
    private List<JCheckBox> checkbox = null;
    private List<GUIControlOptionConfig> options = null;

    public GUICheckboxElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUICheckboxElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "checkbox", template, config, GUICheckboxElementTemplate.class);
    }

    @Override
    public JComponent build() {
        this.panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());

        this.checkbox = new ArrayList<>();
        this.options = new ArrayList<>();
        this.setOptions(this.config.options);
        return this.panel;
    }

    @Override
    public boolean setOptions(GUIControlOptionConfig[] options) {
        if (this.panel == null) {
            return false;
        }

        for (GUIControlOptionConfig option : options) {
            addOption(option);
        }

        return true;
    }

    @Override
    public boolean addOption(GUIControlOptionConfig option) {
        if (this.panel == null || this.checkbox == null || this.options == null || option == null) {
            return false;
        }

        JCheckBox checkboxItem = new JCheckBox(option.getText() == null ? option.getValue() : option.getText(),
                hasChecked(this.value, option.getValue()));
        checkboxItem.setRolloverEnabled(false);
        checkboxItem.setFocusPainted(false);

        super.registerEvents(checkboxItem, this.config.events);
        super.addSubComponent(this.panel, checkboxItem, this.template.getCheckbox());

        this.checkbox.add(checkboxItem);
        this.options.add(option);

        return true;
    }

    @Override
    public boolean cleanOptions() {
        this.options = new ArrayList<>();
        this.checkbox = new ArrayList<>();
        this.panel.removeAll();
        return true;
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        if (data instanceof Collection) {
            Collection<?> listData = (Collection<?>) data;
            this.value = new String[listData.size()];

            int i = 0;
            for (Object dataItem : listData) {
                this.value[i] = dataItem == null ? CheckEmptyUtil.EMPTY : dataItem.toString();
                i++;
            }
        } else {
            this.value = new String[]{data.toString()};
        }

        if (this.checkbox != null && this.options != null) {
            for (int i = 0; i < this.config.options.length; i++) {
                GUIControlOptionConfig option = this.config.options[i];
                this.checkbox.get(i).setSelected(hasChecked(this.value, option.getValue()));
            }
        }

        return true;
    }

    private static boolean hasChecked(String[] values, String currValue) {
        if (values != null) {
            for (String value : values) {
                if (value != null && value.equals(currValue)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Object getValue() {
        if (this.checkbox == null || this.options == null) {
            return Arrays.asList(this.value);
        }

        List<String> data = new ArrayList<>();
        for (int i = 0; i < this.checkbox.size(); i++) {
            if (this.checkbox.get(i).isSelected()) {
                data.add(this.config.options[i].getValue());
            }
        }

        return data.size() > 0 ? data : null;
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return GUIElementCallFunctionHelper.buildOptionFunction(this);
    }

    @Override
    public IGUIElement copy() {
        return new GUICheckboxElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String[]> {
        public GUIControlOptionConfig[] options;
        public EventConfig[] events;
    }
}
