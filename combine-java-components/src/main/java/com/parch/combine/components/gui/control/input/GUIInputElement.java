package com.parch.combine.components.gui.control.input;

import com.parch.combine.components.gui.core.IGUIElement;
import com.parch.combine.components.gui.core.style.ElementHelper;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class GUIInputElement implements IGUIElement {

    private JTextField input = null;

    private GUIInputElementTemplate template;
    private String text;
    private Integer columns;

    public GUIInputElement(GUIInputElementTemplate template, String text, Integer columns) {
        this.template = template == null ? new GUIInputElementTemplate() : template;
        this.text = text;
        this.columns = columns;
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ElementHelper.set(panel, template.getExternal());

        input = new JTextField();
        ElementHelper.set(input, template.getInput());
        if (text != null) {
            input.setText(text);
        }
        if (columns != null) {
            input.setColumns(columns);
        }

        panel.add(input);
        return panel;
    }

    @Override
    public boolean refresh(Object data) {
        if (input == null) {
            return false;
        }

        this.text = data == null ? CheckEmptyUtil.EMPTY : data.toString();
        this.input.setText(this.text);
        return true;
    }

    @Override
    public Object getData() {
        return text;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }
}
