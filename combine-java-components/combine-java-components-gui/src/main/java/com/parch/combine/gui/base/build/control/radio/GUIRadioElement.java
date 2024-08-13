package com.parch.combine.gui.base.build.control.radio;

import com.parch.combine.gui.base.build.GUIControlOptionConfig;
import com.parch.combine.gui.core.call.GUIElementCallFunctionHelper;
import com.parch.combine.gui.core.call.option.IGUIOptionHandler;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JRadioButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUIRadioElement extends AbstractGUIComponentElement<GUIRadioElementTemplate, GUIRadioElement.Config, String> implements IGUIOptionHandler {

    private JPanel panel = null;
    private ButtonGroup radioButton = null;
    private List<JRadioButton> radios = null;
    private List<GUIControlOptionConfig> options = null;

    public GUIRadioElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIRadioElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "radio", template, config, GUIRadioElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel external = new JPanel();
        super.loadTemplates(external, this.template.getExternal());

        this.panel = new JPanel();
        this.radios = new ArrayList<>();
        this.options = new ArrayList<>();
        this.setOptions(this.config.options);

        super.addSubComponent(external, this.panel, this.template.getRadios());
        return external;
    }

    @Override
    public synchronized boolean setOptions(GUIControlOptionConfig[] options) {
        if (options == null) {
            return false;
        }

        this.radioButton = new ButtonGroup();
        this.radios = new ArrayList<>();
        for (GUIControlOptionConfig option : options) {
            this.addOption(option);
        }

        return true;
    }

    @Override
    public boolean addOption(GUIControlOptionConfig option) {
        if (this.panel == null || this.radios == null || this.options == null || option == null || this.radioButton == null) {
            return false;
        }

        JRadioButton radioItem = new JRadioButton(option.getText() == null ? option.getValue() : option.getText(),
                this.value != null && this.value.equals(option.getValue()));

        super.registerEvents(radioItem, this.config.events);
        super.addSubComponent(this.panel, radioItem, this.template.getRadio());

        this.radioButton.add(radioItem);
        this.radios.add(radioItem);
        this.options.add(option);

        return true;
    }

    @Override
    public synchronized boolean cleanOptions() {
        this.options = new ArrayList<>();
        this.radios = new ArrayList<>();
        this.radioButton = null;
        this.panel.removeAll();
        return true;
    }

    @Override
    public synchronized boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        this.value = data.toString();
        if (this.options != null && this.radios != null) {
            for (int i = 0; i < this.options.size(); i++) {
                GUIControlOptionConfig option = this.options.get(i);
                boolean selected = this.value.equals(option.getValue());
                if (this.radios != null) {
                    this.radios.get(i).setSelected(selected);
                }
            }
        }

        return true;
    }

    @Override
    public Object getValue() {
        if (this.radios == null || this.options == null) {
            return this.value;
        }

        for (int i = 0; i < this.radios.size(); i++) {
            if (this.radios.get(i).isSelected()) {
                return this.options.get(i).getValue();
            }
        }

        return null;
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return GUIElementCallFunctionHelper.buildOptionFunction(this);
    }

    @Override
    public IGUIElement copy() {
        return new GUIRadioElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public GUIControlOptionConfig[] options;
        public EventConfig[] events;
    }
}
