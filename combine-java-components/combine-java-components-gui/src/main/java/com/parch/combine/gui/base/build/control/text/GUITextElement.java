package com.parch.combine.gui.base.build.control.text;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Map;

public class GUITextElement extends AbsGUIElement<GUITextElementTemplate, GUITextElement.Config> {

    private JLabel text = null;

    public GUITextElement(String scopeKey, String elementId, Map<String, Object> data, GUITextElementTemplate template, Config config) {
        super(scopeKey, elementId, data, "text", template, config, GUITextElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.text = new JLabel();
        super.loadTemplates(this.text, this.sysTemplate.getText(), this.template.getText());
        this.text.setText(this.config.text);

        panel.add(this.text);
        return panel;
    }

    @Override
    public boolean setValue(Object data) {
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
    public Object getValue() {
        return this.text == null ? this.config.text : this.text.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUITextElement(this.scopeKey, this.id, this.data, this.template, this.config);
    }

    public static class Config {
        public String text;
    }
}
