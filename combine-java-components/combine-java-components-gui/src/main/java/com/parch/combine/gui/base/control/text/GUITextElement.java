package com.parch.combine.gui.base.control.text;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ConstantHelper;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class GUITextElement extends AbsGUIElement<GUITextElementTemplate, GUITextElement.Config> {

    private JLabel text = null;

    public GUITextElement(GUITextElementTemplate template, Config config) {
        super("text", template, config, GUITextElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ConstantHelper.layout(FlowLayout.LEFT));
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.text = new JLabel();
        super.loadTemplates(this.text, this.sysTemplate.getText(), this.template.getText());
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
