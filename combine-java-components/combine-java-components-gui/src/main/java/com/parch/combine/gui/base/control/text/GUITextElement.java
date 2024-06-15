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
        ElementHelper.set(panel, template.getExternal());

        text = new JLabel();
        ElementHelper.set(text, template.getText());
        text.setText(config.text);

        panel.add(text);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (text == null || data == null) {
            return false;
        }

        text.setText(data.toString());
        return true;
    }

    @Override
    public Object getData() {
        return text.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    public static class Config {
        public String text;
    }
}
