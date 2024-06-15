package com.parch.combine.gui.components.control;

import com.parch.combine.gui.base.control.AbsGUIControlComponent;
import com.parch.combine.gui.base.control.select.GUIControSelectLogicConfig;
import com.parch.combine.gui.base.control.select.GUIControlSelectInitConfig;
import com.parch.combine.gui.base.control.select.GUISelectElement;
import com.parch.combine.gui.base.core.IGUIElement;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

@Component(key = "control.select", name = "GUI下拉框控件", logicConfigClass = GUIControSelectLogicConfig.class, initConfigClass = GUIControlSelectInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlSelectComponent extends AbsGUIControlComponent<GUIControlSelectInitConfig, GUIControSelectLogicConfig> {

    public GUIControlSelectComponent() {
        super(GUIControlSelectInitConfig.class, GUIControSelectLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlSelectInitConfig initConfig = getInitConfig();
        GUIControSelectLogicConfig logicConfig = getLogicConfig();

        GUISelectElement.Config config = new GUISelectElement.Config();
        config.value = logicConfig.value();
        config.options = logicConfig.options();
        return new GUISelectElement(initConfig.template(), config);
    }
}
