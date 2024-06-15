package com.parch.combine.gui.components.control;

import com.parch.combine.gui.base.control.AbsGUIControlComponent;
import com.parch.combine.gui.base.control.input.GUIControlInputInitConfig;
import com.parch.combine.gui.base.control.input.GUIControlInputLogicConfig;
import com.parch.combine.gui.base.control.input.GUIInputElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

@Component(key = "control.input", name = "GUI输入框控件", logicConfigClass = GUIControlInputLogicConfig.class, initConfigClass = GUIControlInputInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlInputComponent extends AbsGUIControlComponent<GUIControlInputInitConfig, GUIControlInputLogicConfig> {

    public GUIControlInputComponent() {
        super(GUIControlInputInitConfig.class, GUIControlInputLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlInputInitConfig initConfig = getInitConfig();
        GUIControlInputLogicConfig logicConfig = getLogicConfig();

        GUIInputElement.Config config = new GUIInputElement.Config();
        config.text = logicConfig.text();
        config.columns = logicConfig.columns();
        return new GUIInputElement(initConfig.template(), config);
    }
}
