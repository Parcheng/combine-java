package com.parch.combine.gui.base.control.label;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUILabelElement extends AbsGUIElement<GUILabelElementTemplate, GUILabelElement.Config> {

    private JLabel label = null;

    public GUILabelElement(GUILabelElementTemplate template, Config config) {
        super("label", template, config, GUILabelElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.label = new JLabel();
        super.loadTemplates(this.label, this.sysTemplate.getLabel(), this.template.getLabel());
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
