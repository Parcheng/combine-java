package com.parch.combine.gui.base.control.panel;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.base.control.GUIControlOptionConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ElementStyleConstant;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GUIButtonElement implements IGUIElement {

    private JCheckBox[] checkbox = null;
    private GUIButtonElementTemplate template;
    private Config config;

    public GUIButtonElement(GUIButtonElementTemplate template, Config config) {
        this.template = template == null ? new GUIButtonElementTemplate() : template;
        this.config = config;
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ElementStyleConstant.LEFT_FLOW_LAYOUT);
        ElementHelper.set(panel, template.getExternal());

        checkbox = new JCheckBox[config.options.length];
        for (int i = 0; i < config.options.length; i++) {
            GUIControlOptionConfig option = config.options[i];
            JCheckBox item = new JCheckBox(option.getText() == null ? option.getValue() : option.getText(),
                    hasChecked(config.values, option.getValue()));
            panel.add(item);
            checkbox[i] = item;
        }

        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (checkbox == null || data == null) {
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

        for (int i = 0; i < config.options.length; i++) {
            GUIControlOptionConfig option = config.options[i];
            checkbox[i].setSelected(hasChecked(checkData, option.getValue()));
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
    public Object getData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < checkbox.length; i++) {
            if (checkbox[i].isSelected()) {
                data.add(config.options[i].getValue());
            }
        }

        return data.size() > 0 ? data : null;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    public static class Config {
        public String[] values;
        public GUIControlOptionConfig[] options;
    }
}
