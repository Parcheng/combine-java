package com.parch.combine.gui.base.build.control.input;

import com.parch.combine.gui.core.element.AbsComponentElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.Map;

public class GUIInputElement extends AbsComponentElement<GUIInputElementTemplate, GUIInputElement.Config> {

    private JTextField input = null;

    public GUIInputElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIInputElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "input", template, config, GUIInputElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.input = new JTextField();
        super.loadTemplates(this.input, this.sysTemplate.getInput(), this.template.getInput());
        super.registerEvents(this.input, this.config.events);

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
    public boolean setValue(Object data) {
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
    public Object getValue() {
        return this.input == null ? this.config.text : this.input.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIInputElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config {
        public String text;
        public Integer columns;
        public EventConfig[] events;
    }
}
