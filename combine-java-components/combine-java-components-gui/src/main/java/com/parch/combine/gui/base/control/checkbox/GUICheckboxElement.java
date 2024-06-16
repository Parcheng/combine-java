package com.parch.combine.gui.base.control.checkbox;

import com.parch.combine.gui.base.control.GUIControlOptionConfig;
import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ConstantHelper;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GUICheckboxElement extends AbsGUIElement<GUICheckboxElementTemplate, GUICheckboxElement.Config> {

    private JCheckBox[] checkbox = null;

    public GUICheckboxElement(GUICheckboxElementTemplate template, Config config) {
        super("checkbox", template, config, GUICheckboxElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.checkbox = new JCheckBox[this.config.options.length];
        for (int i = 0; i < this.config.options.length; i++) {
            GUIControlOptionConfig option = this.config.options[i];

            JCheckBox checkboxItem = new JCheckBox(option.getText() == null ? option.getValue() : option.getText(),
                    hasChecked(this.config.values, option.getValue()));
            super.loadTemplates(panel, this.sysTemplate.getCheckbox(), this.template.getCheckbox());

            panel.add(checkboxItem);
            this.checkbox[i] = checkboxItem;
        }

        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (data == null) {
            return false;
        }

        String[] checkData;
        if (data instanceof Collection) {
            Collection<?> listData = (Collection<?>) data;
            checkData = new String[listData.size()];

            int i = 0;
            for (Object dataItem : listData) {
                checkData[i] = dataItem == null ? CheckEmptyUtil.EMPTY : dataItem.toString();
                i++;
            }
        } else {
            checkData = new String[]{data.toString()};
        }

        if (this.checkbox != null) {
            for (int i = 0; i < this.config.options.length; i++) {
                GUIControlOptionConfig option = this.config.options[i];
                this.checkbox[i].setSelected(hasChecked(checkData, option.getValue()));
            }
        }
        this.config.values = checkData;

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
    public Object getData() {
        if (this.checkbox == null) {
            return Arrays.asList(this.config.values);
        }

        List<String> data = new ArrayList<>();
        for (int i = 0; i < this.checkbox.length; i++) {
            if (this.checkbox[i].isSelected()) {
                data.add(this.config.options[i].getValue());
            }
        }

        return data.size() > 0 ? data : null;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUICheckboxElement(this.template, this.config);
    }

    public static class Config {
        public String[] values;
        public GUIControlOptionConfig[] options;
    }
}
