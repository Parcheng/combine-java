package com.parch.combine.gui.base.control.textarea;

import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ElementStyleConstant;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JPanel;

public class GUITextareaElement implements IGUIElement {

    private JTextArea textArea = null;
    private GUITextareaElementTemplate template;
    private Config config;

    public GUITextareaElement(GUITextareaElementTemplate template, Config config) {
        this.template = template == null ? new GUITextareaElementTemplate() : template;
        this.config = config;
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ElementStyleConstant.LEFT_FLOW_LAYOUT);
        ElementHelper.set(panel, template.getExternal());

        textArea = new JTextArea();
        // 自动换行
        textArea.setLineWrap(true);
        ElementHelper.set(textArea, template.getTextArea());

        // 按单词换行
        if (config.isWrapStyleWord != null) {
            textArea.setWrapStyleWord(config.isWrapStyleWord);
        }
        if (config.columns != null) {
            textArea.setColumns(config.columns);
        }
        if (config.value != null) {
            textArea.setText(config.value);
        }

        panel.add(textArea);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (textArea == null || data == null) {
            return false;
        }

        textArea.setText(data.toString());
        return true;
    }

    @Override
    public Object getData() {
        return textArea.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    public static class Config {
        public String value;
        public Integer columns;
        public Boolean isWrapStyleWord;
    }
}
