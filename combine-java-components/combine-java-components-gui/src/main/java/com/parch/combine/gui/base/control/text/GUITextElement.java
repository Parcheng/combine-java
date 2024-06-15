package com.parch.combine.gui.base.control.text;

import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ElementStyleConstant;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUITextElement implements IGUIElement {

    private JLabel text = null;
    private GUITextElementTemplate template;
    private Config config;

    public GUITextElement(GUITextElementTemplate template, Config config) {
        this.template = template == null ? new GUITextElementTemplate() : template;
        this.config = config;
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ElementStyleConstant.LEFT_FLOW_LAYOUT);
        ElementHelper.set(panel, this.template.getExternal());

        this.text = new JLabel();
        ElementHelper.set(this.text, this.template.getText());
        this.text.setText(this.config.text);

        panel.add(this.text);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (data == null) {
            return false;
        }

        if (this.text != null) {
            this.text.setText(data.toString());
        }
        this.config.text = data.toString();

        return true;
    }

    @Override
    public Object getData() {
        return this.text == null ? this.config.text : this.text.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUITextElement(this.template, this.config);
    }

    public static class Config {
        public String text;
    }
}
