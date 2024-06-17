package com.parch.combine.gui.base.control.slider;

import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.*;

public class GUISliderElement extends AbsGUIElement<GUISliderElementTemplate, GUISliderElement.Config> {

    private JSlider slider = null;

    public GUISliderElement(GUISliderElementTemplate template, Config config) {
        super("slider", template, config, GUISliderElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.slider = new JSlider(config.orientation, config.min, config.max, config.value);
        super.loadTemplates(this.slider, this.sysTemplate.getSlider(), this.template.getSlider());

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

        panel.add(this.slider);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
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
        this.config.value = intData;
        return true;
    }

    @Override
    public Object getData() {
        return this.slider == null ? config.value : this.slider.getValue();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUISliderElement(this.template, this.config);
    }

    public static class Config {
        public int orientation;
        public Integer min;
        public Integer max;
        public Integer value;
        public Integer majorTickSpacing;
        public Integer minorTickSpacing;
        public Boolean paintTicks;
        public Boolean paintLabels;
    }
}