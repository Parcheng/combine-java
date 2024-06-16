package com.parch.combine.gui.base.control.textarea;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ConstantHelper;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class GUITextareaElement extends AbsGUIElement<GUITextareaElementTemplate, GUITextareaElement.Config> {

    private JTextArea textArea = null;

    public GUITextareaElement(GUITextareaElementTemplate template, Config config) {
        super("textarea", template, config, GUITextareaElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ConstantHelper.layout(FlowLayout.LEFT));
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.textArea = new JTextArea();
        super.loadTemplates(this.textArea, this.sysTemplate.getTextarea(), this.template.getTextarea());

        // 自动换行
        this.textArea.setLineWrap(true);

        // 按单词换行
        if (this.config.isWrapStyleWord != null) {
            this.textArea.setWrapStyleWord(this.config.isWrapStyleWord);
        }
        if (this.config.columns != null) {
            this.textArea.setColumns(this.config.columns);
        }
        if (this.config.value != null) {
            this.textArea.setText(this.config.value);
        }

        panel.add(this.textArea);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (data == null) {
            return false;
        }

        if (this.textArea != null) {
            this.textArea.setText(data.toString());
        }
        this.config.value = data.toString();

        return true;
    }

    @Override
    public Object getData() {
        return this.textArea == null ? this.config.value : this.textArea.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUITextareaElement(this.template, this.config);
    }

    public static class Config {
        public String value;
        public Integer columns;
        public Boolean isWrapStyleWord;
    }
}
