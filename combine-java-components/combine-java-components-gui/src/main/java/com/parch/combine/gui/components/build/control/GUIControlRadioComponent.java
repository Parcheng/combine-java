package com.parch.combine.gui.components.build.control;

import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.radio.GUIControlRadioInitConfig;
import com.parch.combine.gui.base.build.control.radio.GUIControlRadioLogicConfig;
import com.parch.combine.gui.base.build.control.radio.GUIRadioElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

@Component(key = "build.control.radio", name = "GUI单选框控件", logicConfigClass = GUIControlRadioLogicConfig.class, initConfigClass = GUIControlRadioInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlRadioComponent extends AbstractGUIControlComponent<GUIControlRadioInitConfig, GUIControlRadioLogicConfig> {

    public GUIControlRadioComponent() {
        super(GUIControlRadioInitConfig.class, GUIControlRadioLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlRadioInitConfig initConfig = getInitConfig();
        GUIControlRadioLogicConfig logicConfig = getLogicConfig();

        GUIRadioElement.Config config = new GUIRadioElement.Config();
        super.initConfig(config);
        config.value = logicConfig.value();
        config.options = logicConfig.options();
        config.events = logicConfig.events();
        return new GUIRadioElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
