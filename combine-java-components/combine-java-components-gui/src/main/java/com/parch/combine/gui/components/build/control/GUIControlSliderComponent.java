package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.slider.GUIControlSliderInitConfig;
import com.parch.combine.gui.base.build.control.slider.GUIControlSliderLogicConfig;
import com.parch.combine.gui.base.build.control.slider.GUISliderElement;
import com.parch.combine.gui.base.build.control.slider.SliderOrientationEnum;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.slider", name = "GUI滑块模块", logicConfigClass = GUIControlSliderLogicConfig.class, initConfigClass = GUIControlSliderInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlSliderComponent extends AbstractGUIControlComponent<GUIControlSliderInitConfig, GUIControlSliderLogicConfig> {

    public GUIControlSliderComponent() {
        super(GUIControlSliderInitConfig.class, GUIControlSliderLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlSliderInitConfig initConfig = getInitConfig();
        GUIControlSliderLogicConfig logicConfig = getLogicConfig();

        GUISliderElement.Config config = new GUISliderElement.Config();
        super.initConfig(config);
        config.orientation = SliderOrientationEnum.get(logicConfig.orientation()).getValue();
        config.min = logicConfig.min();
        config.max = logicConfig.max();
        config.value = logicConfig.value();
        config.minorTickSpacing = logicConfig.minorTickSpacing();
        config.majorTickSpacing = logicConfig.majorTickSpacing();
        config.paintLabels = logicConfig.paintLabels();
        config.paintTicks = logicConfig.paintTicks();
        config.events = logicConfig.events();
        return new GUISliderElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
