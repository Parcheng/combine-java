package com.parch.combine.gui.base.control.input;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ConstantHelper;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class GUIInputElement extends AbsGUIElement<GUIInputElementTemplate, GUIInputElement.Config> {

    private JTextField input = null;

    public GUIInputElement(GUIInputElementTemplate template, Config config) {
        super("input", template, config, GUIInputElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ConstantHelper.layout(FlowLayout.LEFT));
        super.loadTemplates(panel, this.template.getExternal(), this.template.getExternal());

        this.input = new JTextField();
        super.loadTemplates(this.input, this.template.getInput(), this.template.getInput());

        if (this.config.text != null) {
            this.input.setText(this.config.text);
        }
        if (this.config.columns != null) {
            this.input.setColumns(this.config.columns);
        }

        panel.add(this.input);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (data == null) {
            data = CheckEmptyUtil.EMPTY;
        }

        if (this.input != null) {
            this.input.setText(data.toString());
        }
        this.config.text = data.toString();

        return true;
    }

    @Override
    public Object getData() {
        return this.input == null ? this.config.text : this.input.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIInputElement(this.template, this.config);
    }

    public static class Config {
        public String text;
        public Integer columns;
    }
}
