package com.parch.combine.gui.components.control;

import com.parch.combine.gui.base.control.AbsGUIControlComponent;
import com.parch.combine.gui.base.control.radio.GUIControlRadioInitConfig;
import com.parch.combine.gui.base.control.radio.GUIControlRadioLogicConfig;
import com.parch.combine.gui.base.control.radio.GUIRadioElement;
import com.parch.combine.gui.base.core.IGUIElement;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

@Component(key = "control.radio", name = "GUI单选框控件", logicConfigClass = GUIControlRadioLogicConfig.class, initConfigClass = GUIControlRadioInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlRadioComponent extends AbsGUIControlComponent<GUIControlRadioInitConfig, GUIControlRadioLogicConfig> {

    public GUIControlRadioComponent() {
        super(GUIControlRadioInitConfig.class, GUIControlRadioLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlRadioInitConfig initConfig = getInitConfig();
        GUIControlRadioLogicConfig logicConfig = getLogicConfig();

        GUIRadioElement.Config config = new GUIRadioElement.Config();
        config.value = logicConfig.value();
        config.options = logicConfig.options();
        return new GUIRadioElement(initConfig.template(), config);
    }
}
