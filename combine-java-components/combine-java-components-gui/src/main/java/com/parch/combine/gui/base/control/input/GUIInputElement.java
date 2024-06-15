package com.parch.combine.gui.base.control.input;

import com.parch.combine.gui.base.core.IGUIElement;
import com.parch.combine.gui.base.core.style.ElementHelper;
import com.parch.combine.gui.base.core.style.ElementStyleConstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class GUIInputElement implements IGUIElement {

    private JTextField input = null;
    private GUIInputElementTemplate template;

    private Config config;

    public GUIInputElement(GUIInputElementTemplate template, Config config) {
        this.template = template == null ? new GUIInputElementTemplate() : template;
        this.config = config;
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ElementStyleConstant.LEFT_FLOW_LAYOUT);
        ElementHelper.set(panel, template.getExternal());

        input = new JTextField();
        ElementHelper.set(input, template.getInput());
        if (config.text != null) {
            input.setText(config.text);
        }
        if (config.columns != null) {
            input.setColumns(config.columns);
        }

        panel.add(input);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        this.input.setText(data == null ? CheckEmptyUtil.EMPTY : data.toString());
        return true;
    }

    @Override
    public Object getData() {
        return input.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    public static class Config {
        public String text;
        public Integer columns;
    }
}
