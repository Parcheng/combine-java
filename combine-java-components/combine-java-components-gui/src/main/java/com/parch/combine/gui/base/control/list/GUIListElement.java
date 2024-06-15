package com.parch.combine.gui.base.control.list;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.base.control.GUIControlOptionConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ElementStyleConstant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GUIListElement implements IGUIElement {

    private JCheckBox[] checkbox = null;
    private GUIListElementTemplate template;
    private Config config;

    public GUIListElement(GUIListElementTemplate template, Config config) {
        this.template = template == null ? new GUIListElementTemplate() : template;
        this.config = config;
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ElementStyleConstant.LEFT_FLOW_LAYOUT);
        ElementHelper.set(panel, template.getExternal());

        DefaultListModel<JComponent> listModel = new DefaultListModel<>();
        JList<JComponent> list = new JList<>(listModel);
        list.setCellRenderer((list1, value, index, isSelected, cellHasFocus) -> value);

//        for (IGUIElement element : config.elements) {
//            listModel.addElement(element.build());
//        }

        panel.add(new JScrollPane(list), BorderLayout.CENTER);
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
