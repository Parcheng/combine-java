package com.parch.combine.gui.base.build.control.textarea;

import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.util.Map;

public class GUITextareaElement extends AbstractGUIComponentElement<GUITextareaElementTemplate, GUITextareaElement.Config, String> {

    private JTextArea textArea = null;

    public GUITextareaElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUITextareaElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "textarea", template, config, GUITextareaElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());

        this.textArea = new JTextArea();
        super.registerEvents(this.textArea, this.config.events);

        // 自动换行
        this.textArea.setLineWrap(true);

        // 按单词换行
        if (this.config.isWrapStyleWord != null) {
            this.textArea.setWrapStyleWord(this.config.isWrapStyleWord);
        }
        if (this.config.columns != null) {
            this.textArea.setColumns(this.config.columns);
        }
        if (this.value != null) {
            this.textArea.setText(this.value);
        }

        super.addSubComponent(panel, this.textArea,  this.template.getTextarea());
        return panel;
    }

    @Override
    public synchronized boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        if (this.textArea != null) {
            this.textArea.setText(data.toString());
        }
        this.value = data.toString();

        return true;
    }

    @Override
    public Object getValue() {
        return this.textArea == null ? this.value : this.textArea.getText();
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUITextareaElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public Integer columns;
        public Boolean isWrapStyleWord;
        public EventConfig[] events;
    }
}
