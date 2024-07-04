package com.parch.combine.gui.base.build.control.input;

import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.Map;

public class GUIInputElement extends AbstractGUIComponentElement<GUIInputElementTemplate, GUIInputElement.Config, String> {

    private JTextField input = null;

    public GUIInputElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIInputElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "input", template, config, GUIInputElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.addSymbol(panel, this.config.prefix);

        this.input = new JTextField();
        super.loadTemplates(this.input, this.sysTemplate.getInput(), this.template.getInput());
        super.registerEvents(this.input, this.config.events);

        if (this.value != null) {
            this.input.setText(this.value);
        }
        if (this.config.columns != null) {
            this.input.setColumns(this.config.columns);
        }

        panel.add(this.input);

        this.addSymbol(panel, this.config.suffix);
        return panel;
    }

    public void addSymbol(JPanel panel, String text) {
        if (CheckEmptyUtil.isEmpty(text)) {
            return;
        }

        JLabel label = new JLabel(text);
        super.loadTemplates(label, this.sysTemplate.getSymbol(), this.template.getSymbol());
        panel.add(label);
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            data = CheckEmptyUtil.EMPTY;
        }

        if (this.input != null) {
            this.input.setText(data.toString());
        }
        this.value = data.toString();

        return true;
    }

    @Override
    public Object getValue() {
        return this.input == null ? this.value : this.input.getText();
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIInputElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public Integer columns;
        public EventConfig[] events;
        public String prefix;
        public String suffix;
    }
}
