package com.parch.combine.gui.base.control.label;

import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ConstantHelper;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class GUILabelElement implements IGUIElement {

    private JLabel label = null;
    private GUILabelElementTemplate template;
    private Config config;

    public GUILabelElement(GUILabelElementTemplate template, Config config) {
        this.template = template == null ? new GUILabelElementTemplate() : template;
        this.config = config;
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ConstantHelper.layout(FlowLayout.LEFT));
        ElementHelper.set(panel, this.template.getExternal());

        this.label = new JLabel();
        ElementHelper.set(this.label, this.template.getLabel());
        this.label.setText(this.config.text);

        panel.add(this.label);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (data == null) {
            return false;
        }

        if (this.label != null) {
            this.label.setText(data.toString());
        }
        this.config.text = data.toString();

        return true;
    }

    @Override
    public Object getData() {
        return this.label == null ? config.text : this.label.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUILabelElement(this.template, this.config);
    }

    public static class Config {
        public String text;
    }
}
