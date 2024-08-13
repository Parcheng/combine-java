package com.parch.combine.gui.base.build.control.slider;

import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.JPanel;
import java.util.Map;

public class GUISliderElement extends AbstractGUIComponentElement<GUISliderElementTemplate, GUISliderElement.Config, Integer> {

    private JSlider slider = null;

    public GUISliderElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUISliderElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "slider", template, config, GUISliderElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel,  this.template.getExternal());

        this.slider = new JSlider(config.orientation, config.min, config.max, config.value);
        super.registerEvents(this.slider, this.config.events);

        if (this.config.majorTickSpacing != null) {
            this.slider.setMajorTickSpacing(config.majorTickSpacing);
        }
        if (this.config.minorTickSpacing != null) {
            this.slider.setMinorTickSpacing(config.minorTickSpacing);
        }
        if (this.config.paintTicks != null) {
            this.slider.setPaintTicks(this.config.paintTicks);
        }
        if (this.config.paintLabels != null) {
            this.slider.setPaintLabels(this.config.paintLabels);
        }

        super.addSubComponent(panel, this.slider, this.template.getSlider());
        return panel;
    }

    @Override
    public synchronized boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        Integer intData = DataParseUtil.getInteger(data, null);
        if (intData == null) {
            return false;
        }

        if (this.slider != null) {
            this.slider.setValue(intData);
        }
        this.value = intData;
        return true;
    }

    @Override
    public Object getValue() {
        return this.slider == null ? this.value : this.slider.getValue();
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUISliderElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<Integer> {
        public int orientation;
        public Integer min;
        public Integer max;
        public Integer majorTickSpacing;
        public Integer minorTickSpacing;
        public Boolean paintTicks;
        public Boolean paintLabels;
        public EventConfig[] events;
    }
}
